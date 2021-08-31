package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.ScheduleExtraWork

class ExtraScheduleAdapter(
    val context: Context,
    val extrascheduleList: MutableList<ScheduleExtraWork>
): RecyclerView.Adapter<ExtraScheduleAdapter.ExtraScheduleViewHolder>() {

    class ExtraScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        val txtName : TextView
        val txtDate : TextView
        val txtMessage : TextView
        val txtAcceptedby : TextView
        val txtSubject : TextView
        val ExtraworkAcceptbackground : ImageView
        val ExtraworkAccepted : TextView
        val ExtraWorkAcceptorNot : FrameLayout
        init{
//            txtName = view.findViewById(R.id.txtName)
            txtDate = view.findViewById(R.id.txtDate)
            txtMessage = view.findViewById(R.id.txtMessage)
            txtAcceptedby = view.findViewById(R.id.txtAcceptedby)
            txtSubject = view.findViewById(R.id.txtSubject)
            ExtraworkAcceptbackground = view.findViewById(R.id.ExtraworkAcceptbackground)
            ExtraworkAccepted = view.findViewById(R.id.ExtraworkAccepted)
            ExtraWorkAcceptorNot = view.findViewById(R.id.ExtraWorkAcceptorNot)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_extrawork, parent, false)
        return ExtraScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExtraScheduleViewHolder, position: Int) {
        val extraschedule = extrascheduleList[position]
        holder.txtDate.text = extraschedule.scheduledDate + "." + extraschedule.scheduledTime
        holder.txtMessage.text = extraschedule.message
        holder.txtAcceptedby.text = extraschedule.acceptedBy
        holder.txtSubject.text = extraschedule.subject

        holder.ExtraWorkAcceptorNot.setOnClickListener {
            holder.ExtraworkAcceptbackground.setBackgroundResource(R.drawable.ic_toggle_accepted)
            holder.ExtraworkAccepted.text = "Accepted"
        }
    }

    override fun getItemCount(): Int {
        return extrascheduleList.size
    }
}