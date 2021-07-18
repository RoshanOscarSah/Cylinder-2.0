package com.eachut.cylinder.ui.profiles

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentProfilesBinding
import com.eachut.cylinder.entity.Member


class AddmemberFragment : Fragment() {

    private lateinit var profilesViewModel: ProfilesViewModel
    private var _binding: FragmentProfilesBinding? = null
    private lateinit var etUsername : TextView
    private lateinit var lstname : TextView
    private lateinit var phonenum : TextView
    private lateinit var address : TextView
    private lateinit var cylincom : TextView
    private lateinit var loginbtn : TextView

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        @SuppressLint("NewApi")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            profilesViewModel =
                ViewModelProvider(this).get(ProfilesViewModel::class.java)
            _binding = FragmentProfilesBinding.inflate(inflater, container, false)
            val root: View = binding.root
            etUsername = root.findViewById(R.id.etUsername)
            lstname = root.findViewById(R.id.lstname)
            phonenum = root.findViewById(R.id.phonenum)
            address = root.findViewById(R.id.address)
            cylincom = root.findViewById(R.id.cylincom)
            loginbtn = root.findViewById(R.id.loginbtn)
            loginbtn.setOnClickListener {
                val first_name = etUsername.text.toString()
                val last_name = lstname.text.toString()
                val phone_number = phonenum.text.toString()
                val address = address.text.toString()
                val comission_percent = cylincom.text.toString()
//                val member = Member(Firstname = first_name, Lastname = last_name, Status = Status, Phonenumber = phone_number,Address = address,Comission = comission_percent)
//                CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val memberRepository = MemberRepository()
//                    val response = memberRepository.addnewmemberadmin(member)
//                    if (response.success == true){
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@AddNewMemberActivity,
//                                "Member Added Successfully",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                    else{
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@AddNewMemberActivity,
//                                "Member Not Added",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                } catch (ex: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@AddNewMemberActivity,
//                            ex.toString(),
//                            Toast.LENGTH_SHORT).show()
//                    }
//                }
//                }
            }
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