package com.eachut.cylinder.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.ui.profiles.GetResellerProfile

class CompanyProfileAdapter(
    val context : Context,
    val companyList :MutableList<Company>
): RecyclerView.Adapter<CompanyProfileAdapter.CompanyProfileViewHolder>(){

    class CompanyProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtCylindername :TextView
        val txtFcname :TextView
        val txtCaddress :TextView
        val tv_Halfcylinder :TextView
        val tv_leckcyclinder :TextView
        val tv_Burn :TextView
        val tv_Cylinder :TextView
        val iv_Call : LinearLayout

        init {
            txtCylindername = view.findViewById(R.id.txtCylindername)
            txtFcname = view.findViewById(R.id.txtFcname)
            txtCaddress = view.findViewById(R.id.txtCaddress)
            tv_Halfcylinder = view.findViewById(R.id.tv_Halfcylinder)
            tv_leckcyclinder = view.findViewById(R.id.tv_leckcyclinder)
            tv_Burn = view.findViewById(R.id.tv_Burn)
            tv_Cylinder = view.findViewById(R.id.tv_Cylinder)
            iv_Call = view.findViewById(R.id.ivCall1)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewcompany, parent, false)
        return CompanyProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyProfileViewHolder, position: Int) {
        val company = companyList[position]
        holder.txtCylindername.text=company.cylinder_name
        holder.txtFcname.text=company.company_fullname
        holder.txtCaddress.text=company.address
        holder.iv_Call.setContentDescription(company.phone_number)
        holder.iv_Call.setOnClickListener {
            val number = holder.iv_Call.getContentDescription()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}