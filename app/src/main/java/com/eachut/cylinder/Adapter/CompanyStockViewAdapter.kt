package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.Object.ResellerDetails
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company

class CompanyStockViewAdapter (
    val context: Context,
    val companyList: MutableList<Company>
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
    ): CompanyStockViewAdapter.CompanyStockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popup_view_company, parent, false)
        return CompanyStockViewAdapter.CompanyStockViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CompanyStockViewAdapter.CompanyStockViewHolder,
        position: Int
    ) {
        val company = companyList[position]
        holder.txtCylindername.text=company.cylinder_name
        holder.txtFcname.text=company.company_fullname
        holder.txtCaddress.text=company.address

        holder.llNameSelected.setOnClickListener {
            CompanyDetails.setCompany(company)
        }
    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}