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
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionManager
import com.eachut.cylinder.R
import com.eachut.cylinder.WelcomeActivity
import com.eachut.cylinder.databinding.FragmentStockBinding
import com.eachut.cylinder.ui.stock.StockViewModel

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


}