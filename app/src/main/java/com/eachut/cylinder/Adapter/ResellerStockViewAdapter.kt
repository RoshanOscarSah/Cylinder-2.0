package com.eachut.cylinder.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Object.ResellerDetails
import com.eachut.cylinder.Object.ResellerStockDetails
import com.eachut.cylinder.R
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.ResellerStockRepository
import com.eachut.cylinder.ui.home.HomeFragment
import com.eachut.cylinder.ui.profiles.GetResellerProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

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
        val llNameSelected : LinearLayout
        init{
            tv_Fullname = view.findViewById(R.id.tv_Fullname)
            Tv_Pasalname = view.findViewById(R.id.Tv_Pasalname)
            tv_Address = view.findViewById(R.id.tv_Address)
            llNameSelected = view.findViewById(R.id.llNameSelected)
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
        holder.llNameSelected.setOnClickListener {
            ResellerDetails.setReseller(reseller)
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val resellerStockRepo = ResellerStockRepository()
                    val response = resellerStockRepo.singleresellerStockList(reseller._id!!)
                    withContext(Dispatchers.Main) {
//                        println(response.success)
//                        Toast.makeText(
//                            context,
//                            "Response : ${response.success}",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        Log.d("UNISH","Response : ${response}")
                        if (response.success == true) {
                            ResellerStockDetails.setResellerStockDetails(response.data!!)
                            withContext(Main) {
                                Toast.makeText(
                                    context,
                                    "data from database: ${response.data}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        else if(response.success == false && response.data == null) {
                            withContext(Main) {
                                Toast.makeText(context, "Couldn't find data", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        else{
                            withContext(Main) {
                                Toast.makeText(context, "Else", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                    }
                }
                catch(e:Exception)
                {
                    withContext(Main){
                        print(e)
                        Toast.makeText(context, "Could Not Find Data in Database", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return resellerList.size    }
}