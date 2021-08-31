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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.repository.CompanyStockRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import com.eachut.cylinder.ui.profiles.GetResellerProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyProfileAdapter(
    val context : Context,
    val companyList :MutableList<Company>
): RecyclerView.Adapter<CompanyProfileAdapter.CompanyProfileViewHolder>(){

    class CompanyProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_Tag: TextView
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
            tv_Tag = view.findViewById(R.id.tv_Tag)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewcompany, parent, false)
        return CompanyProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyProfileViewHolder, position: Int) {
        val company = companyList[position]

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repo = CompanyStockRepository()
                val response = repo.CompanyDetails(company._id!!)
                if (response.success== true){
                    holder.tv_leckcyclinder.text = response.LeakCylinderGiven
                    holder.tv_Burn.text = response.GasSold
                    holder.tv_Cylinder.text = response.CylinderSold
                    holder.tv_Tag.text = response.Rate
                    holder.tv_Halfcylinder.text = response.CylinderLended

                }
                else{
                    holder.tv_leckcyclinder.text = 0.toString()
                    holder.tv_Burn.text = 0.toString()
                    holder.tv_Cylinder.text = 0.toString()
                    holder.tv_Tag.text = "null"
                    holder.tv_Halfcylinder.text = 0.toString()
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }

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