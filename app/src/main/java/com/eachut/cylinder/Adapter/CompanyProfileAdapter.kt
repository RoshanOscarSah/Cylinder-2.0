package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company

class CompanyProfileAdapter(
    val context : Context,
    val companyList :MutableList<Company>
): RecyclerView.Adapter<CompanyProfileAdapter.CompanyProfileViewHolder>(){
    class CompanyProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtCname :TextView
        val txtFcname :TextView
        val txtCaddress :TextView
        val tv_Halfcylinder :TextView
        val tv_leckcyclinder :TextView
        val tv_Burn :TextView
        val tv_Cylinder :TextView
        init {
            txtCname = view.findViewById(R.id.txtCname)
            txtFcname = view.findViewById(R.id.txtFcname)
            txtCaddress = view.findViewById(R.id.txtCaddress)
            tv_Halfcylinder = view.findViewById(R.id.tv_Halfcylinder)
            tv_leckcyclinder = view.findViewById(R.id.tv_leckcyclinder)
            tv_Burn = view.findViewById(R.id.tv_Burn)
            tv_Cylinder = view.findViewById(R.id.tv_Cylinder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewresellerprofile, parent, false)
        return CompanyProfileAdapter.CompanyProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyProfileViewHolder, position: Int) {
        val company = companyList[position]
        holder.txtCname.text=company.cylinder_name
        holder.txtFcname.text=company.company_fullname
        holder.txtCaddress.text=company.address
    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}