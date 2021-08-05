package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.ScheduleExtraWork

class ExtraScheduleAdapter(
    val context: Context,
    val extrascheduleList: MutableList<ScheduleExtraWork>
): RecyclerView.Adapter<ExtraScheduleAdapter.ExtraScheduleViewHolder>() {

    class ExtraScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtName : TextView
        val txtDate : TextView
        val txtMessage : TextView
        val txtAcceptedby : TextView
        val txtSubject : TextView
        init{
            txtName = view.findViewById(R.id.txtName)
            txtDate = view.findViewById(R.id.txtDate)
            txtMessage = view.findViewById(R.id.txtMessage)
            txtAcceptedby = view.findViewById(R.id.txtAcceptedby)
            txtSubject = view.findViewById(R.id.txtSubject)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_extrawork, parent, false)
        return ExtraScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExtraScheduleViewHolder, position: Int) {
        val extraschedule = extrascheduleList[position]
        holder.txtDate.text = extraschedule.scheduledDate
        holder.txtMessage.text = extraschedule.message
        holder.txtAcceptedby.text = extraschedule.acceptedBy
        holder.txtSubject.text = extraschedule.subject
    }

    override fun getItemCount(): Int {
        return extrascheduleList.size
    }
}