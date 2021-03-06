package com.eachut.cylinder.ui.profiles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.ResellerProfileAdapter
import com.eachut.cylinder.Object.ResellerList
import com.eachut.cylinder.Object.ResellerStockDetails
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentDashboardBinding
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetResellerProfile() : Fragment() {

//    private var resellerList = mutableListOf<Reseller>()
    private lateinit var recyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_get_reseller_profile, container, false)
        recyclerview = view.findViewById(R.id.recyclerview)


//        get reseller
        val resellerList= ResellerList.getResellerList()

        Log.d("ResellerList", resellerList.toString())

        recyclerview.adapter = ResellerProfileAdapter(requireContext(), resellerList)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())




        return view
    }


}