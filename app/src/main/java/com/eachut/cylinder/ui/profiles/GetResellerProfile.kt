package com.eachut.cylinder.ui.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.ResellerProfileAdapter
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetResellerProfile : Fragment() {

    private var resellerList = mutableListOf<Reseller>()
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_get_reseller_profile, container, false)
        recyclerview = view.findViewById(R.id.recyclerview)
        //get my cart
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resellserRepository = ResellerRepository()
                val response = resellserRepository.allresellerList()
                if(response.success==true){
                    // Put all the student details in lstStudents
                    resellerList = response.data!!
                    withContext(Dispatchers.Main){
//                        Toast.makeText(context, "$cartList", Toast.LENGTH_SHORT).show()
                        recyclerview.adapter = ResellerProfileAdapter(requireContext(), resellerList)
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