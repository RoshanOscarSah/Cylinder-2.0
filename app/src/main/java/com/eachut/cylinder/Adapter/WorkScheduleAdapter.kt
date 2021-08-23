package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkScheduleAdapter(
    val context: Context,
    val workscheduleList: MutableList<ScheduleResellerStock>
): RecyclerView.Adapter<WorkScheduleAdapter.WorkScheduleViewHolder>() {

    class WorkScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_schedule, parent, false)
        return WorkScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkScheduleViewHolder, position: Int) {
        val workschedule = workscheduleList[position]
        val total = workschedule.Leak_Kamakhya!!.toInt()+workschedule.Leak_Others!!.toInt()+
                workschedule.Leak_Prima!!.toInt()+workschedule.Leak_Suvidha!!.toInt()+workschedule.Regular_Kamakhya!!.toInt()+
                workschedule.Regular_Prima!!.toInt()+workschedule.Regular_Suvidha!!.toInt()+workschedule.Regular_Others!!.toInt()+
                workschedule.Sold_Kamakhya!!.toInt()+workschedule.Sold_Suvidha!!.toInt()+workschedule.Sold_Prima!!.toInt()+
                workschedule.Sold_Others!!.toInt()

        holder.txtDetail.text="${workschedule.SendOrReceive}.${workschedule.Gas_state}.P.$total"

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val resellerRepo = ResellerRepository()
                val response = resellerRepo.resellerList(workschedule.ResellerID!!)
                if(response.success!!){
                    val reseller = response.info!!
                    holder.txtName.text = reseller.reseller_fullname
                    holder.txtCname.text = reseller.pasal_name
                    holder.txtCaddress.text = reseller.address

                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return workscheduleList.size
    }
}