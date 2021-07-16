package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Reseller

class ResellerProfileAdapter (
    val context: Context,
    val resellerList: MutableList<Reseller>
): RecyclerView.Adapter<ResellerProfileAdapter.ResellerProfileViewHolder>(){
    class ResellerProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_Fullname :TextView
        val Tv_Pasalname :TextView
        val tv_Address :TextView
        val TV_Price :TextView
        val tv_Halfcylinder :TextView
        val tv_leckcyclinder :TextView
        val tv_Tag :TextView
        val tv_Burn :TextView
        val tv_Cylinder :TextView
        init{
            tv_Fullname = view.findViewById(R.id.tv_Fullname)
            Tv_Pasalname = view.findViewById(R.id.Tv_Pasalname)
            tv_Address = view.findViewById(R.id.tv_Address)
            TV_Price = view.findViewById(R.id.TV_Price)
            tv_Halfcylinder = view.findViewById(R.id.tv_Halfcylinder)
            tv_leckcyclinder = view.findViewById(R.id.tv_leckcyclinder)
            tv_Tag = view.findViewById(R.id.tv_Tag)
            tv_Burn = view.findViewById(R.id.tv_Burn)
            tv_Cylinder = view.findViewById(R.id.tv_Cylinder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResellerProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewresellerprofile, parent, false)
        return ResellerProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResellerProfileViewHolder, position: Int) {
        val reseller = resellerList[position]
        holder.tv_Fullname.text=reseller.reseller_fullname
        holder.Tv_Pasalname.text=reseller.pasal_name
        holder.tv_Address.text=reseller.address
    }

    override fun getItemCount(): Int {
        return resellerList.size
    }
}