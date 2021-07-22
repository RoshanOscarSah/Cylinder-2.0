package com.eachut.cylinder.ui.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.MemberProfileAdapter
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.repository.MemberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewMemberFragment : Fragment() {

    private lateinit var recyclerview : RecyclerView
    private var memberList = mutableListOf<Member>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_member, container, false)

        recyclerview = view.findViewById(R.id.recyclerview)

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