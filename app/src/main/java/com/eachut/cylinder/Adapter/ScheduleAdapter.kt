package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleAdapter(
    val context: Context,
    val scheduleList: MutableList<ScheduleResellerStock>
    ): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtName : TextView
        val txtCname : TextView
        val txtCaddress : TextView
        val txtDetail : TextView
        init{
            txtName = view.findViewById(R.id.txtName)
            txtCname = view.findViewById(R.id.txtCname)
            txtCaddress = view.findViewById(R.id.txtCaddress)
            txtDetail = view.findViewById(R.id.txtDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_schedule, parent, false)
        return ScheduleAdapter.ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = scheduleList[position]
        val total = schedule.Leak_Kamakhya!!.toInt()+schedule.Leak_Others!!.toInt()+
                schedule.Leak_Prima!!.toInt()+schedule.Leak_Suvidha!!.toInt()+schedule.Regular_Kamakhya!!.toInt()+
                schedule.Regular_Prima!!.toInt()+schedule.Regular_Suvidha!!.toInt()+schedule.Regular_Others!!.toInt()+
                schedule.Sold_Kamakhya!!.toInt()+schedule.Sold_Suvidha!!.toInt()+schedule.Sold_Prima!!.toInt()+
                schedule.Sold_Others!!.toInt()
        holder.txtDetail.text="${schedule.SendOrReceive}.${schedule.Gas_state}.P.$total"
        CoroutineScope(Dispatchers.IO).launch{
            val resellerRepo = ResellerRepository()
            val response = resellerRepo.resellerList(schedule.ResellerID!!)
            if(response.success!!){
                val reseller = response.reseller!!
                holder.txtName.text = reseller.reseller_fullname
                holder.txtCname.text = reseller.pasal_name
                holder.txtCaddress.text = reseller.address
            }
        }
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

}