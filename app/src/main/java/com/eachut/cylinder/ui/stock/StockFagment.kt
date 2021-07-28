package com.eachut.cylinder.ui.stock

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionManager
import com.eachut.cylinder.R
import com.eachut.cylinder.WelcomeActivity
import com.eachut.cylinder.databinding.FragmentStockBinding
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.MemberRepository
import com.eachut.cylinder.repository.ResellerRepository
import com.eachut.cylinder.repository.StockRepository
import com.eachut.cylinder.ui.stock.StockViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StockFragment : Fragment() {

    private lateinit var stockViewModel: StockViewModel
    private var _binding: FragmentStockBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stockViewModel =
            ViewModelProvider(this).get(StockViewModel::class.java)

        _binding = FragmentStockBinding.inflate(inflater, container, false)
        val root: View = binding.root


        loadStock()

//EDIT STOCK
        binding.flStockEdit.setOnClickListener { view ->
            binding.flStockEditCancal.isVisible = true
            binding.flStockEditDone.isVisible = true
            binding.flStockEdit.isVisible = false

            binding.etPrimaF.setFocusableInTouchMode(true)
            binding.etKamakhyaF.setFocusableInTouchMode(true)
            binding.etSuvidhaF.setFocusableInTouchMode(true)
            binding.etOthersF.setFocusableInTouchMode(true)
            binding.etPrimaH.setFocusableInTouchMode(true)
            binding.etKamakhyaH.setFocusableInTouchMode(true)
            binding.etSuvidhaH.setFocusableInTouchMode(true)
            binding.etOthersH.setFocusableInTouchMode(true)
            binding.etPrimaE.setFocusableInTouchMode(true)
            binding.etKamakhyaE.setFocusableInTouchMode(true)
            binding.etSuvidhaE.setFocusableInTouchMode(true)
            binding.etOthersE.setFocusableInTouchMode(true)
        }

        binding.flStockEditCancal.setOnClickListener { view ->
            binding.flStockEditCancal.isVisible = false
            binding.flStockEditDone.isVisible = false
            binding.flStockEdit.isVisible = true

            binding.etPrimaF.setFocusable(false)
            binding.etKamakhyaF.setFocusable(false)
            binding.etSuvidhaF.setFocusable(false)
            binding.etOthersF.setFocusable(false)
            binding.etPrimaH.setFocusable(false)
            binding.etKamakhyaH.setFocusable(false)
            binding.etSuvidhaH.setFocusable(false)
            binding.etOthersH.setFocusable(false)
            binding.etPrimaE.setFocusable(false)
            binding.etKamakhyaE.setFocusable(false)
            binding.etSuvidhaE.setFocusable(false)
            binding.etOthersE.setFocusable(false)
        }

        binding.flStockEditDone.setOnClickListener { view ->
            binding.flStockEditCancal.isVisible = false
            binding.flStockEditDone.isVisible = false
            binding.flStockEdit.isVisible = true

            binding.etPrimaF.setFocusable(false)
            binding.etKamakhyaF.setFocusable(false)
            binding.etSuvidhaF.setFocusable(false)
            binding.etOthersF.setFocusable(false)
            binding.etPrimaH.setFocusable(false)
            binding.etKamakhyaH.setFocusable(false)
            binding.etSuvidhaH.setFocusable(false)
            binding.etOthersH.setFocusable(false)
            binding.etPrimaE.setFocusable(false)
            binding.etKamakhyaE.setFocusable(false)
            binding.etSuvidhaE.setFocusable(false)
            binding.etOthersE.setFocusable(false)
        }

//for setting
        binding.setting2.setOnClickListener { view ->
            val animation = ObjectAnimator.ofFloat(binding.setting2, "rotation", 0f, 180f)
            animation.duration = 500
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()

            //starting popup
            showSetting2()

        }

//        val textView: TextView = binding.textStock
//        stockViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // for setting popup
    fun showSetting2() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_presetting, null)

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {

            }
        })
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create();

        alertDialog.show();
        val lp = WindowManager.LayoutParams()

        lp.copyFrom(alertDialog.window!!.attributes)
        lp.height = 1250
        lp.x = 0
        lp.y = -120
        alertDialog.getWindow()!!.setAttributes(lp);
        alertDialog.getWindow()!!.setBackgroundDrawableResource(R.color.dark_fade);
        alertDialog.setCanceledOnTouchOutside(true);


    }


    fun loadStock() {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val stockRepo = StockRepository()
                val stockResponse = stockRepo.viewStock()
                if (stockResponse.success == true) {

                    Log.d("OSCAR","Aa: $stockResponse")
                    binding.etPrimaF.setHint("${stockResponse.etPrimaF}");
                    binding.etPrimaF.setText("${stockResponse.etPrimaF}");
                    binding.etPrimaH.setHint("${stockResponse.etPrimaH}");
                    binding.etPrimaH.setText("${stockResponse.etPrimaH}");
                    binding.etPrimaE.setHint("${stockResponse.etPrimaE}");
                    binding.etPrimaE.setText("${stockResponse.etPrimaE}");

                    binding.etKamakhyaF.setHint("${stockResponse.etKamakhyaF}");
                    binding.etKamakhyaF.setText("${stockResponse.etKamakhyaF}");
                    binding.etKamakhyaH.setHint("${stockResponse.etKamakhyaH}");
                    binding.etKamakhyaH.setText("${stockResponse.etKamakhyaH}");
                    binding.etKamakhyaE.setHint("${stockResponse.etKamakhyaE}");
                    binding.etKamakhyaE.setText("${stockResponse.etKamakhyaE}");

                    binding.etSuvidhaF.setHint("${stockResponse.etSuvidhaF}");
                    binding.etSuvidhaF.setText("${stockResponse.etSuvidhaF}");
                    binding.etSuvidhaH.setHint("${stockResponse.etSuvidhaH}");
                    binding.etSuvidhaH.setText("${stockResponse.etSuvidhaH}");
                    binding.etSuvidhaE.setHint("${stockResponse.etSuvidhaE}");
                    binding.etSuvidhaE.setText("${stockResponse.etSuvidhaE}");

                    binding.etOthersF.setHint("${stockResponse.etOthersF}");
                    binding.etOthersF.setText("${stockResponse.etOthersF}");
                    binding.etOthersH.setHint("${stockResponse.etOthersH}");
                    binding.etOthersH.setText("${stockResponse.etOthersH}");
                    binding.etOthersE.setHint("${stockResponse.etOthersE}");
                    binding.etOthersE.setText("${stockResponse.etOthersE}");

                    var etTotalF = stockResponse.etPrimaF + stockResponse.etKamakhyaF + stockResponse.etSuvidhaF + stockResponse.etOthersF
                    var etTotalH = stockResponse.etPrimaH + stockResponse.etKamakhyaH + stockResponse.etSuvidhaH + stockResponse.etOthersH
                    var etTotalE = stockResponse.etPrimaE + stockResponse.etKamakhyaE + stockResponse.etSuvidhaE + stockResponse.etOthersE
                    var etTotalT = etTotalF + etTotalH + etTotalE

                    binding.etTotalF.setText("$etTotalF");
                    binding.etTotalH.setText("$etTotalH");
                    binding.etTotalE.setText("$etTotalE");
                    binding.etTotalT.setText("$etTotalT");

                    var etPrimaT = stockResponse.etPrimaF + stockResponse.etPrimaH + stockResponse.etPrimaE
                    var etKamakhyaT = stockResponse.etKamakhyaF + stockResponse.etKamakhyaH + stockResponse.etKamakhyaE
                    var etSuvidhaT = stockResponse.etSuvidhaF + stockResponse.etSuvidhaH + stockResponse.etSuvidhaE
                    var etOthersT = stockResponse.etOthersF + stockResponse.etOthersH + stockResponse.etOthersE

                    binding.etTotalF.setText("$etPrimaT");
                    binding.etTotalH.setText("$etKamakhyaT");
                    binding.etTotalE.setText("$etSuvidhaT");
                    binding.etTotalE.setText("$etOthersT");




                } else {
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "${stockResponse.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}