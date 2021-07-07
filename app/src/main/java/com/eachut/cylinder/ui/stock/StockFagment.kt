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




}