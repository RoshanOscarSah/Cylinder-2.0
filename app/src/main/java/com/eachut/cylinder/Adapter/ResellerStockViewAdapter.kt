package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.ui.home.HomeFragment
import com.eachut.cylinder.ui.profiles.GetResellerProfile

class ResellerStockViewAdapter(
    val context: Context,
    val resellerList: MutableList<Reseller>
): RecyclerView.Adapter<ResellerStockViewAdapter.ResellerStockViewHolder>() {
    private var _binding: HomeFragment? = null

    private val binding get() = _binding!!

    class ResellerStockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_Fullname : TextView
        val Tv_Pasalname : TextView
        val tv_Address : TextView
        init{
            tv_Fullname = view.findViewById(R.id.tv_Fullname)
            Tv_Pasalname = view.findViewById(R.id.Tv_Pasalname)
            tv_Address = view.findViewById(R.id.tv_Address)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResellerStockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popup_view_reseller, parent, false)
        return ResellerStockViewAdapter.ResellerStockViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResellerStockViewHolder, position: Int) {
        val reseller = resellerList[position]
        holder.tv_Fullname.text=reseller.reseller_fullname
        holder.Tv_Pasalname.text=reseller.pasal_name
        holder.tv_Address.text=reseller.address
    }

    override fun getItemCount(): Int {
        return resellerList.size    }
}