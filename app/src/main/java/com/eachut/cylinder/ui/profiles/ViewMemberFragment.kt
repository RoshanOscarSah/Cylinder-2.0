package com.eachut.cylinder.ui.profiles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.MemberProfileAdapter
import com.eachut.cylinder.LoginActivity
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.repository.MemberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewMemberFragment : Fragment() {

    private lateinit var logout : Button

    private lateinit var recyclerview : RecyclerView
    private var memberList = mutableListOf<Member>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_member, container, false)

        recyclerview = view.findViewById(R.id.recyclerview)
        logout = view.findViewById(R.id.logout)

//        logout
        logout.setOnClickListener { view ->
//            val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
//            editor.putString("Username","")
//            editor.putString("Password","")
//            editor.apply()

            val pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val edt = pref.edit()
            edt.putString("token", "")
            edt.commit()

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

        }


        //get member
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val memberRepository = MemberRepository()
                val response = memberRepository.allmemberList()
                if(response.success==true){
                    // Put all the student details in lstStudents
                    memberList = response.data!!
                    withContext(Dispatchers.Main){
//                        Toast.makeText(context, "$cartList", Toast.LENGTH_SHORT).show()
                        recyclerview.adapter = MemberProfileAdapter(requireContext(), memberList)
                        recyclerview.layoutManager = LinearLayoutManager(context)
                    }
                }
                else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,
                            response.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }catch(ex : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,
                        "Error : $ex", Toast.LENGTH_SHORT).show()
                }
            }

        }

        return view
    }
}