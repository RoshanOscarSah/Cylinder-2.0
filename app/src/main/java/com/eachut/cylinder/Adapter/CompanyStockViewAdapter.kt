package com.eachut.cylinder.Adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.Object.CompanyStockDetails
import com.eachut.cylinder.Object.ResellerDetails
import com.eachut.cylinder.Object.ResellerStockDetails
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.repository.CompanyStockRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyStockViewAdapter(
    val context: Context,
    val companyList: MutableList<Company>,
    val alertDialog: AlertDialog
): RecyclerView.Adapter<CompanyStockViewAdapter.CompanyStockViewHolder>(){
    class CompanyStockViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtCylindername : TextView
        val txtFcname : TextView
        val txtCaddress : TextView
        val llNameSelected : LinearLayout
        init {
            txtCylindername = view.findViewById(R.id.txtCylindername)
            txtFcname = view.findViewById(R.id.txtFcname)
            txtCaddress = view.findViewById(R.id.txtCaddress)
            llNameSelected = view.findViewById(R.id.llNameSelected)

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyStockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popup_view_company, parent, false)
        return CompanyStockViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CompanyStockViewHolder,
        position: Int
    ) {
        val company = companyList[position]
        holder.txtCylindername.text=company.cylinder_name
        holder.txtFcname.text=company.company_fullname
        holder.txtCaddress.text=company.address

        holder.llNameSelected.setOnClickListener {
            CompanyDetails.setCompany(company)
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val companyStockRepo = CompanyStockRepository()
                    val response = companyStockRepo.singlecompanyStockList(company._id!!)
                    withContext(Dispatchers.Main) {
//                        println(response.success)
//                        Toast.makeText(
//                            context,
//                            "Response : ${response.success}",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        Log.d("UNISH","Response : ${response}")
                        if (response.success == true) {
                            CompanyStockDetails.setCompanyStockDetails(response.data!!)

                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "data from database: ${response.data}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        else if(response.success == false && response.data == null) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Couldn't find data", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        else{
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Else", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                    }
                }
                catch(e:Exception)
                {
                    withContext(Dispatchers.Main){
                        print(e)
                        Toast.makeText(context, "Could Not Find Data in Database", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            alertDialog.dismiss()
        }

    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}