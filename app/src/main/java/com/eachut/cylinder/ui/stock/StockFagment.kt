package com.eachut.cylinder.ui.stock

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentStockBinding
import com.eachut.cylinder.entity.Stock
import com.eachut.cylinder.repository.StockRepository
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

            updateStock()
        }

//for setting
//        binding.setting2.setOnClickListener { view ->
//            val animation = ObjectAnimator.ofFloat(binding.setting2, "rotation", 0f, 180f)
//            animation.duration = 500
//            animation.interpolator = AccelerateDecelerateInterpolator()
//            animation.start()
//
//            //starting popup
//            showSetting2()
//
//        }

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
//    fun showSetting2() {
//        val inflater: LayoutInflater = this.getLayoutInflater()
//        val dialogView: View = inflater.inflate(R.layout.activity_presetting, null)
//
//        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
//            override fun onDismiss(arg0: DialogInterface) {
//
//            }
//        })
//        dialogBuilder.setView(dialogView)
//
//        val alertDialog = dialogBuilder.create();
//
//        alertDialog.show();
//        val lp = WindowManager.LayoutParams()
//
//        lp.copyFrom(alertDialog.window!!.attributes)
//        lp.height = 1250
//        lp.x = 0
//        lp.y = -120
//        alertDialog.getWindow()!!.setAttributes(lp);
//        alertDialog.getWindow()!!.setBackgroundDrawableResource(R.color.dark_fade);
//        alertDialog.setCanceledOnTouchOutside(true);
//
//
//    }

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

                    Log.d("OSCAR", "data : ${stockResponse.etPrimaF}")

                    var etPrimaT = stockResponse.etPrimaF!!.toInt() + stockResponse.etPrimaH!!.toInt() + stockResponse.etPrimaE!!.toInt()
                    var etKamakhyaT = stockResponse.etKamakhyaF!!.toInt() + stockResponse.etKamakhyaH!!.toInt() + stockResponse.etKamakhyaE!!.toInt()
                    var etSuvidhaT = stockResponse.etSuvidhaF!!.toInt() + stockResponse.etSuvidhaH!!.toInt() + stockResponse.etSuvidhaE!!.toInt()
                    var etOthersT = stockResponse.etOthersF!!.toInt() + stockResponse.etOthersH!!.toInt() + stockResponse.etOthersE!!.toInt()

                    Log.d("OSCAR", "data : ${stockResponse.etPrimaF}")
                    Log.d("OSCAR", "data : ${stockResponse.etPrimaF?.toInt()}")
                    Log.d("OSCAR", "etPrimaT : $etPrimaT")
                    binding.etPrimaT.text = etPrimaT.toString()
                    binding.etKamakhyaT.text = etKamakhyaT.toString()
                    binding.etSuvidhaT.text = etSuvidhaT.toString()
                    binding.etOthersT.text = etOthersT.toString()

                    var etTotalF = stockResponse.etPrimaF!!.toInt() + stockResponse.etKamakhyaF!!.toInt() + stockResponse.etSuvidhaF!!.toInt() + stockResponse.etOthersF!!.toInt()
                    var etTotalH = stockResponse.etPrimaH!!.toInt() + stockResponse.etKamakhyaH!!.toInt() + stockResponse.etSuvidhaH!!.toInt() + stockResponse.etOthersH!!.toInt()
                    var etTotalE = stockResponse.etPrimaE!!.toInt() + stockResponse.etKamakhyaE!!.toInt() + stockResponse.etSuvidhaE!!.toInt()+ stockResponse.etOthersE!!.toInt()
                    var etTotalT = etTotalF + etTotalH + etTotalE

                    binding.etTotalF.setText("$etTotalF");
                    binding.etTotalH.setText("$etTotalH");
                    binding.etTotalE.setText("$etTotalE");
                    binding.etTotalT.setText("$etTotalT");



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

    fun updateStock() {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val etPrimaFA = binding.etPrimaF.text.toString() //300
                val etPrimaFB = binding.etPrimaF.getHint().toString(); //290
                var etPrimaFD = etPrimaFA.toInt() - etPrimaFB.toInt()  //10

                val etPrimaHA = binding.etPrimaH.text.toString()
                val etPrimaHB = binding.etPrimaH.getHint().toString();
                var etPrimaHD = etPrimaHA.toInt() - etPrimaHB.toInt()

                val etPrimaEA = binding.etPrimaE.text.toString()
                val etPrimaEB = binding.etPrimaE.getHint().toString();
                val etPrimaED = etPrimaEA.toInt() - etPrimaEB.toInt()



                val etKamakhyaFA = binding.etKamakhyaF.text.toString()
                val etKamakhyaFB = binding.etKamakhyaF.getHint().toString();
                val etKamakhyaFD = etKamakhyaFA.toInt() - etKamakhyaFB.toInt()


                val etKamakhyaHA = binding.etKamakhyaH.text.toString()
                val etKamakhyaHB = binding.etKamakhyaH.getHint().toString();
                val etKamakhyaHD = etKamakhyaHA.toInt() - etKamakhyaHB.toInt()


                val etKamakhyaEA = binding.etKamakhyaE.text.toString()
                val etKamakhyaEB = binding.etKamakhyaE.getHint().toString();
                val etKamakhyaED = etKamakhyaEA.toInt() - etKamakhyaEB.toInt()

                val etSuvidhaFA = binding.etSuvidhaF.text.toString()
                val etSuvidhaFB = binding.etSuvidhaF.getHint().toString();
                val etSuvidhaFD = etSuvidhaFA.toInt() - etSuvidhaFB.toInt()

                val etSuvidhaHA = binding.etSuvidhaH.text.toString()
                val etSuvidhaHB = binding.etSuvidhaH.getHint().toString();
                val etSuvidhaHD = etSuvidhaHA.toInt() - etSuvidhaHB.toInt()

                val etSuvidhaEA = binding.etSuvidhaE.text.toString()
                val etSuvidhaEB = binding.etSuvidhaE.getHint().toString();
                val etSuvidhaED = etSuvidhaEA.toInt() - etSuvidhaEB.toInt()

                val etOthersFA = binding.etOthersF.text.toString()
                val etOthersFB = binding.etOthersF.getHint().toString();
                val etOthersFD = etOthersFA.toInt() - etOthersFB.toInt()

                val etOthersHA = binding.etOthersH.text.toString()
                val etOthersHB = binding.etOthersH.getHint().toString();
                val etOthersHD = etOthersHA.toInt() - etOthersHB.toInt()

                val etOthersEA = binding.etOthersE.text.toString()
                val etOthersEB = binding.etOthersE.getHint().toString();
                val etOthersED = etOthersEA.toInt() - etOthersEB.toInt()

                val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val Entryby = sharedPreferences.getString("Username","Admin")

                Log.d("OSCAR","Entryby: $Entryby")

                val stock = Stock(
                    etPrimaF = etPrimaFD,
                    etPrimaH = etPrimaHD,
                    etPrimaE = etPrimaED,
                    etKamakhyaF = etKamakhyaFD,
                    etKamakhyaH = etKamakhyaHD,
                    etKamakhyaE = etKamakhyaED,
                    etSuvidhaF = etSuvidhaFD,
                    etSuvidhaH = etSuvidhaHD,
                    etSuvidhaE = etSuvidhaED,
                    etOthersF = etOthersFD,
                    etOthersH = etOthersHD,
                    etOthersE = etOthersED,
                    Entryby = Entryby,
                )
                val stockRepo = StockRepository()
                val stockResponse = stockRepo.updateStock(stock)
                if (stockResponse.success == true) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "${stockResponse.message}", Toast.LENGTH_SHORT).show()

                        loadStock()
                    }
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