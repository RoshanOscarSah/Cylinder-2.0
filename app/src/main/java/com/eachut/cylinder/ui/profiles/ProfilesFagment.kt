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
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eachut.cylinder.Adapter.ResellerStockViewAdapter
import com.eachut.cylinder.AddNewMemberActivity
import com.eachut.cylinder.Object.CompanyList
import com.eachut.cylinder.Object.MemberList
import com.eachut.cylinder.Object.ResellerList
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentProfilesBinding
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.MemberRepository
import com.eachut.cylinder.repository.ResellerRepository
import com.eachut.cylinder.ui.profiles.ProfilesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilesFragment : Fragment() {

    private lateinit var profilesViewModel: ProfilesViewModel
    private var _binding: FragmentProfilesBinding? = null
    private var resellerList = mutableListOf<Reseller>()
    private var sortedReseller = resellerList

    private var companyList = mutableListOf<Company>()
    private var sortedCompany = companyList

    private var memberList = mutableListOf<Member>()
    private var sortedMember = memberList





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


        CoroutineScope(Dispatchers.IO).launch {
            val resellerRepo = ResellerRepository()

            val companyRepo = CompanyRepository()
            val memberRepo = MemberRepository()
            val resellerResponse = resellerRepo.allresellerList()
            val companyResponse = companyRepo.allCompanyList()
            val memberResponse = memberRepo.allmemberList()
            if (resellerResponse.success!!) {
                resellerList = resellerResponse.data!!
                ResellerList.setResellerList(sortedReseller)
            }
            if (companyResponse.success!!) {
                companyList = companyResponse.data!!
            }
            if (memberResponse.success!!) {
                memberList = memberResponse.data!!
            }
        }

            //Loading Reseller Profile
            val fragment = GetResellerProfile()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, fragment)
            fragmentTransaction.remove(fragment)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            _binding = FragmentProfilesBinding.inflate(inflater, container, false)
            val root: View = binding.root

            //Add member/reseller/company
            binding.add.setOnClickListener { view ->

                val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.NO_GRAVITY
                binding.ivToggleActiveP.setLayoutParams(params);

                val intent = Intent(this.context, AddNewMemberActivity::class.java)
                startActivity(intent)

//            val fragment = AddmemberFragment()
//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(android.R.id.content, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
            }

//customer/company/member
            binding.tvCustomerP.setOnClickListener { view ->
                //for setting gravity
                val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.LEFT
                binding.ivToggleActiveP.setLayoutParams(params);

                val fragment = GetResellerProfile()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(android.R.id.content, fragment)
                fragmentTransaction.remove(fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                fragmentTransaction.remove(fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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

                val fragment = ViewMemberFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(android.R.id.content, fragment)
                fragmentTransaction.remove(fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
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
            binding.ivFilterProfiles.setOnClickListener {

                val popupMenu: PopupMenu =
                    PopupMenu(this.context, view?.findViewById(R.id.ivFilterProfiles))
                popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.ascending -> {
                            //sort reseller

                            sortedReseller =
                                resellerList.sortedWith(compareBy { it.pasal_name!!.first() }) as MutableList<Reseller>
                            ResellerList.setResellerList(sortedReseller)


//                            //sort Company
//                            sortedCompany =
//                                companyList.sortedWith(compareBy { it.cylinder_name!!.first() }) as MutableList<Company>
//                            CompanyList.setCompanyList(sortedCompany)
//
//                            //sort Member
//                            sortedMember =
//                                memberList.sortedBy { it.Firstname!!.first() } as MutableList<Member>
//                            MemberList.setMemberList(sortedMember)
////                        Toast.makeText(context, "$sortedReseller", Toast.LENGTH_SHORT).show()
                        }
                        R.id.descending -> {
                            //sort reseller
                            sortedReseller =
                                resellerList.sortedByDescending { it.pasal_name!!.first() } as MutableList<Reseller>
                            ResellerList.setResellerList(sortedReseller)

//                        //sort company
//                        sortedCompany =
//                            companyList.sortedByDescending { it.cylinder_name!!.first() } as MutableList<Company>
//                        CompanyList.setCompanyList(sortedCompany)
//
//                        //sort member
//                        sortedMember = memberList.sortedByDescending { it.Firstname!!.first() } as MutableList<Member>
//                        MemberList.setMemberList(sortedMember)
////                        Toast.makeText(context, "$sortedReseller", Toast.LENGTH_SHORT).show()
                        }
                        R.id.mostsold -> {
                            Toast.makeText(view?.context, "Most Sold", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                })
                popupMenu.show()

//        val textView: TextView = binding.textProfiles
//        profilesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
            }
            return root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}