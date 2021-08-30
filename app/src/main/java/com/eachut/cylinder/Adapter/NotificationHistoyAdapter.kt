package com.eachut.cylinder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.NotificationHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationHistoyAdapter(
    val context : Context,
    val notificationHistoryList :MutableList<NotificationHistory>
): RecyclerView.Adapter<NotificationHistoyAdapter.NotificationHistoryViewHolder>() {

    class NotificationHistoryViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val NotificationType : TextView
        val NotificationL1 : TextView
        val NotificationL2 : TextView
        val NotificationL3 : TextView
        val NotificationR1 : TextView
//        val NotificationR2 : FrameLayout

        init {
            NotificationType = view.findViewById(R.id.NotificationType)
            NotificationL1 = view.findViewById(R.id.NotificationL1)
            NotificationL2 = view.findViewById(R.id.NotificationL2)
            NotificationL3 = view.findViewById(R.id.NotificationL3)
            NotificationR1 = view.findViewById(R.id.NotificationR1)
//            NotificationR2 = view.findViewById(R.id.NotificationR2)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.allnotification, parent , false)
        return NotificationHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationHistoryViewHolder, position: Int) {
        var notificationhistory = notificationHistoryList[position]
        holder.NotificationType.text = notificationhistory.Title
        holder.NotificationL1.text = notificationhistory.L1
        holder.NotificationL2.text = notificationhistory.L2
        holder.NotificationL3.text = notificationhistory.L3
        holder.NotificationR1.text = notificationhistory.R1
    }

    override fun getItemCount(): Int {
        return notificationHistoryList.size
    }
}