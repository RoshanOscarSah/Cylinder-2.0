package com.eachut.cylinder.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.repository.MemberRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repo = MemberRepository()
                val response = repo.MemberDetails(member._id!!)
                if (response.success== true){
                    holder.TV_Price.text = response.Amount
                    holder.tv_leakcylinder.text = response.LeakCylinderGiven
                    holder.tv_Burn.text = response.GasSold
                    holder.tv_Cylinder.text = response.CylinderSold
                    holder.tv_Tag.text = response.Rate
                    holder.tv_Halfcylinder.text = response.CylinderLended

                }
                else{
                    holder.TV_Price.text = 0.toString()
                    holder.tv_leakcylinder.text = 0.toString()
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
