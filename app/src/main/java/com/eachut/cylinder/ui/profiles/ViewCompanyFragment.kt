package com.eachut.cylinder.ui.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.CompanyProfileAdapter
import com.eachut.cylinder.Adapter.ResellerProfileAdapter
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewCompanyFragment : Fragment() {

    private lateinit var recyclerview : RecyclerView
    private var companyList = mutableListOf<Company>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_company, container, false)
        recyclerview = view.findViewById(R.id.recyclerview)
        //get company
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val companyRepository = CompanyRepository()
                val response = companyRepository.allCompanyList()
                if(response.success==true){
                    // Put all the student details in lstStudents
                    companyList = response.data!!
                    withContext(Dispatchers.Main){
//                        Toast.makeText(context, "$cartList", Toast.LENGTH_SHORT).show()
                        recyclerview.adapter = CompanyProfileAdapter(requireContext(), companyList)
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