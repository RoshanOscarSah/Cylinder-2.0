package com.eachut.cylinder.ui.profiles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentProfilesBinding
import com.eachut.cylinder.ui.profiles.ProfilesViewModel

class ProfilesFragment : Fragment() {

    private lateinit var profilesViewModel: ProfilesViewModel
    private var _binding: FragmentProfilesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilesViewModel =
            ViewModelProvider(this).get(ProfilesViewModel::class.java)

        _binding = FragmentProfilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//customer/company/member
        binding.tvCustomerP.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            binding.ivToggleActiveP.setLayoutParams(params);

            val fragment = ViewCompanyFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.tvCompanyP.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            binding.ivToggleActiveP.setLayoutParams(params);

            val fragment = ViewCompanyFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.tvMemberP.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            binding.ivToggleActiveP.setLayoutParams(params);
        }

//search
        binding.ivSearchProfiles.setOnClickListener { view ->
            binding.flTab3.isVisible = false
            binding.ivSearchProfiles.isVisible = false
            binding.llsearchBox.isVisible = true
            binding.ivCancalSearchProfiles.isVisible = true
        }

        binding.ivCancalSearchProfiles.setOnClickListener { view ->
            binding.flTab3.isVisible = true
            binding.ivSearchProfiles.isVisible = true
            binding.llsearchBox.isVisible = false
            binding.ivCancalSearchProfiles.isVisible = false
        }

        //add


// FILTER
        binding.ivFilterProfiles.setOnClickListener { view ->

        }

//        val textView: TextView = binding.textProfiles
//        profilesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}