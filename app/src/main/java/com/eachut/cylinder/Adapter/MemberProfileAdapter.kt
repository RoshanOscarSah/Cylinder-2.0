package com.eachut.cylinder.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Member
import org.w3c.dom.Text

class MemberProfileAdapter (
    val context : Context,
    val memberList :MutableList<Member>
        ): RecyclerView.Adapter<MemberProfileAdapter.MemberProfileViewHolder>(){
    class MemberProfileViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tv_Fullname : TextView
    val tv_employee : TextView
    val tv_Address : TextView
    val TV_Price : TextView
    val tv_Halfcylinder:TextView
    val tv_leakcylinder : TextView
    val tv_Tag : TextView
    val tv_Burn : TextView
    val tv_Cylinder : TextView
    val iv_Call : ImageView
    init {
        tv_Fullname = view.findViewById(R.id.tv_Fullname)
        tv_employee = view.findViewById(R.id.tv_employee)
        tv_Address = view.findViewById(R.id.tv_Address)
        TV_Price = view.findViewById(R.id.TV_Price)
        tv_Halfcylinder = view.findViewById(R.id.tv_Halfcylinder)
        tv_leakcylinder = view.findViewById(R.id.tv_leckcyclinder)
        tv_Tag = view.findViewById(R.id.tv_Tag)
        tv_Burn = view.findViewById(R.id.tv_Burn)
        tv_Cylinder = view.findViewById(R.id.tv_Cylinder)
        iv_Call = view.findViewById(R.id.iv_Call)
    }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewmember, parent, false)
        return MemberProfileViewHolder(view)    }

    override fun onBindViewHolder(holder: MemberProfileViewHolder, position: Int) {
        val member = memberList[position]
        holder.tv_Fullname.text = "${member.Firstname.toString()}  ${member.Lastname.toString()}"
        holder.tv_Address.text=member.Address
        holder.tv_employee.text=member.Status
        holder.iv_Call.setContentDescription(member.Phonenumber)
        holder.iv_Call.setOnClickListener {
            val number = holder.iv_Call.getContentDescription()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
            return memberList.size
    }

}
