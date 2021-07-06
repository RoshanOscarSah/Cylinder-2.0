package com.eachut.cylinder.ui.profiles

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentProfilesBinding


class AddmemberFragment : Fragment() {

    private lateinit var profilesViewModel: ProfilesViewModel
    private var _binding: FragmentProfilesBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            profilesViewModel =
                ViewModelProvider(this).get(ProfilesViewModel::class.java)

            _binding = FragmentProfilesBinding.inflate(inflater, container, false)
            val root: View = binding.root

//Admin / Employee
//            binding.llAdmin.setOnClickListener { view ->
//
//                //for sliding animation
//                ObjectAnimator.ofFloat(binding.ivSendCheck, "translationX", 0f).apply {
//                    duration = 200
//                    start()
//                }
//
//                //for sliding animation
//                ObjectAnimator.ofFloat(binding.ivRecieveCheck, "translationX", -250f).apply {
//                    duration = 200
//                    start()
//                }
//
//
//
//                binding.ivRecieveCheck.isVisible = false
//                binding.ivSendCheck.isVisible = true
//
//                binding.llemploye.background.setTintList(context?.let {
//                    ContextCompat.getColorStateList(
//                        it, R.color.sendreceive_fade)
//                })
//
//                binding.llAdmin.background.setTintList(context?.let {
//                    ContextCompat.getColorStateList(
//                        it, R.color.sendreceive)
//                })
//            }
//
//            binding.llemploye.setOnClickListener { view ->
//
//                //for sliding animation
//                ObjectAnimator.ofFloat(binding.ivSendCheck, "translationX", 460f).apply {
//                    duration = 200
//                    start()
//                }
//
//                //for sliding animation
//                ObjectAnimator.ofFloat(binding.ivRecieveCheck, "translationX", 210f).apply {
//                    duration = 200
//                    start()
//                }
//
//                binding.ivRecieveCheck.isVisible = true
//                binding.ivSendCheck.isVisible = false
//
//                binding.llemploye.background.setTintList(context?.let {
//                    ContextCompat.getColorStateList(
//                        it, R.color.sendreceive)
//                })
//
//                binding.llAdmin.background.setTintList(context?.let {
//                    ContextCompat.getColorStateList(
//                        it, R.color.sendreceive_fade)
//                })
//            }



//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
            return root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }