package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.CompanyStockRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception

class ReceiptActivity : AppCompatActivity() {
    private lateinit var txttitlename : TextView
    private lateinit var txtaddress : TextView
    private lateinit var txtCname : TextView
    private lateinit var txtSname : TextView
    private lateinit var txtFull : TextView
    private lateinit var txtDate : TextView
    private lateinit var txtSerialno : TextView
    private lateinit var txtSend : TextView
    private lateinit var txtprimanumber : TextView
    private lateinit var txtkamakhyanumber : TextView
    private lateinit var txtsubhidanumber : TextView
    private lateinit var txtothersnumber : TextView
    private lateinit var txtRate : TextView
    private lateinit var txtLeak : TextView
    private lateinit var txtPurchase : TextView
    private lateinit var txtSold : TextView
    private lateinit var txtCylinder : TextView
    private lateinit var txtCash : TextView
    private lateinit var btnSubmit:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        txttitlename = findViewById(R.id.txttitlename)
        txtaddress = findViewById(R.id.txtaddress)
        txtCname = findViewById(R.id.txtCname)
        txtSname = findViewById(R.id.txtSname)
        txtFull = findViewById(R.id.txtFull)
        txtDate = findViewById(R.id.txtDate)
        txtSerialno = findViewById(R.id.txtSerialno)
        txtSend = findViewById(R.id.txtSend)
        txtprimanumber = findViewById(R.id.txtprimanumber)
        txtkamakhyanumber = findViewById(R.id.txtkamakhyanumber)
        txtsubhidanumber = findViewById(R.id.txtsubhidanumber)
        txtothersnumber = findViewById(R.id.txtothersnumber)
        txtRate = findViewById(R.id.txtRate)
        txtLeak = findViewById(R.id.txtLeak)
        txtPurchase = findViewById(R.id.txtPurchase)
        txtSold = findViewById(R.id.txtSold)
        txtCylinder = findViewById(R.id.txtCylinder)
        txtCash = findViewById(R.id.txtCash)
        btnSubmit = findViewById(R.id.btnSubmit)


        val status = intent.getStringExtra("status")


                            //Company
        if(status=="company"){
            val company = intent.getParcelableExtra<CompanyStock>("companyStock")!!
            val companyInfo = intent.getParcelableExtra<Company>("company")!!
            txtFull.setText("${company.Gas_state}")
            txtprimanumber.setText("${company.Regular_Prima}")
            txtkamakhyanumber.setText("${company.Regular_Kamakhya}")
            txtsubhidanumber.setText("${company.Regular_Kamakhya}")
            txtothersnumber.setText("${company.Regular_Prima}")
            txtCname.setText("${companyInfo.cylinder_name}")
            txtSname.setText("${companyInfo.company_fullname }")
            txtPurchase.setText("${company.Amount}")
            txtSerialno.setText("${companyInfo._id}")
            txtLeak.text= (company.Leak_Prima!!.toInt() + company.Leak_Kamakhya!!.toInt() + company.Leak_Suvidha!!.toInt() + company.Leak_Others!!.toInt()).toString()
            txtSold.text = (company.Sold_Prima!!.toInt()+ company.Sold_Kamakhya!!.toInt() + company.Sold_Suvidha!!.toInt() + company.Sold_Others!!.toInt()).toString()

            btnSubmit.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val companyRepository  = CompanyStockRepository()
                        val companyResponse = companyRepository.addCompanyStock(company)
                        if (companyResponse.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${companyResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${companyResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }catch(e:Exception){
                        withContext(Main){
                            Toast.makeText(this@ReceiptActivity, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }


        }
        if (status=="reseller"){
            val reseller = intent.getParcelableExtra<ResellerStock>("resellerStock")!!
            val resellerInfo = intent.getParcelableExtra<Reseller>("reseller")!!
            txtFull.setText("${reseller.Gas_state}")
            txtprimanumber.setText("${reseller.Regular_Prima}")
            txtkamakhyanumber.setText("${reseller.Regular_Kamakhya}")
            txtsubhidanumber.setText("${reseller.Regular_Kamakhya}")
            txtothersnumber.setText("${reseller.Regular_Prima}")
            txtCname.setText("${resellerInfo.reseller_fullname}")
            txtSname.setText("${resellerInfo.pasal_name}")
            txtPurchase.setText("${reseller.Amount}")
            txtSerialno.setText("${resellerInfo._id}")
            txtLeak.text= (reseller.Leak_Prima!!.toInt() + reseller.Leak_Kamakhya!!.toInt() + reseller.Leak_Suvidha!!.toInt() + reseller.Leak_Others!!.toInt()).toString()
            txtSold.text = (reseller.Sold_Prima!!.toInt()+ reseller.Sold_Kamakhya!!.toInt() + reseller.Sold_Suvidha!!.toInt() + reseller.Sold_Others!!.toInt()).toString()

            btnSubmit.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val resellerRepository  = ResellerStockRepository()
                        val resellerResponse = resellerRepository.addResellerStock(reseller)
                        if (resellerResponse.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${resellerResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${resellerResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }

                }catch (e:Exception){
                    withContext(Main){
                        Toast.makeText(this@ReceiptActivity, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }

                    }
                }

            }

        }



    }



}