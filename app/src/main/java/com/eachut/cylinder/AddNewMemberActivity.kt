package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.eachut.cylinder.entity.Reseller
import kotlinx.coroutines.*

class AddNewMemberActivity : AppCompatActivity() {

    private lateinit var etUsername : TextView
    private lateinit var etCompanyname : TextView
    private lateinit var etResellerfullname : TextView

    private lateinit var lstname : TextView
    private lateinit var cylindername : TextView
    private lateinit var pasalname : TextView

    private lateinit var resellerphonenum : TextView
    private lateinit var companyphonenum : TextView
    private lateinit var memberphonenum : TextView

    private lateinit var reselleraddress : TextView
    private lateinit var companyaddress : TextView
    private lateinit var memberaddress : TextView

    private lateinit var commission : LinearLayout
    private lateinit var cylincom : TextView
    private lateinit var loginbtn : TextView

    private lateinit var tvMemberP:TextView
    private lateinit var tvCompanyP:TextView
    private lateinit var tvCustomerP:TextView

    private lateinit var status:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_member)

        etUsername = findViewById(R.id.etUsername)
        etCompanyname = findViewById(R.id.etCompanyname)
        etResellerfullname = findViewById(R.id.etResellerfullname)

        lstname = findViewById(R.id.lstname)
        cylindername = findViewById(R.id.cylindername)
        pasalname = findViewById(R.id.pasalname)

        resellerphonenum = findViewById(R.id.resellerphonenum)
        companyphonenum = findViewById(R.id.companyphonenum)
        memberphonenum = findViewById(R.id.memberphonenum)

        reselleraddress = findViewById(R.id.reselleraddress)
        companyaddress = findViewById(R.id.companyaddress)
        memberaddress = findViewById(R.id.memberaddress)

        commission = findViewById(R.id.commission)
        cylincom = findViewById(R.id.cylincom)
        loginbtn = findViewById(R.id.loginbtn)

        tvMemberP = findViewById(R.id.tvMemberP)
        tvCompanyP = findViewById(R.id.tvCompanyP)
        tvCustomerP = findViewById(R.id.tvCustomerP)

        status = findViewById(R.id.status)

        etCompanyname.isVisible = false
        cylindername.isVisible = false
        companyaddress.isVisible = false
        companyphonenum.isVisible = false
        etResellerfullname.isVisible = false
        pasalname.isVisible = false
        reselleraddress.isVisible = false
        resellerphonenum.isVisible = false
        etUsername.isVisible = true
        lstname.isVisible = true
        memberaddress.isVisible = true
        memberphonenum.isVisible = true
        cylincom.isVisible = true

        MemberForm()
        CompanyForm()
        ResellerForm()

        loginbtn.setOnClickListener {

            val first_name = etUsername.text.toString()
            val last_name = lstname.text.toString()
            val phone_number = resellerphonenum.text.toString()
            val address = reselleraddress.text.toString()
            val comission_percent = cylincom.text.toString()

//            val user = User(first_name = first_name, last_name = last_name,phone_number = phone_number,address = address,comission_percent = comission_percent)

//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val userRepository = UserRepository()
//                    val response = userRepository.addnewmemberadmin(user)
//                    if (response.success == true){
//                        startActivity(Intent(this@AddNewMemberActivity,AddNewMemberActivity::class.java))
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@AddNewMemberActivity,
//                                "Member Successful",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                    else{
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@AddNewMemberActivity,
//                                "Member Not Added",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                } catch (ex: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@AddNewMemberActivity,
//                            ex.toString(),
//                            Toast.LENGTH_SHORT).show()
//                    }
//                }
//                }
            }

        }

    private fun MemberForm()
    {
        tvMemberP.setOnClickListener {
            etCompanyname.isVisible = false
            cylindername.isVisible = false
            companyaddress.isVisible = false
            companyphonenum.isVisible = false
            etResellerfullname.isVisible = false
            pasalname.isVisible = false
            reselleraddress.isVisible = false
            resellerphonenum.isVisible = false
            etUsername.isVisible = true
            lstname.isVisible = true
            memberaddress.isVisible = true
            memberphonenum.isVisible = true
            cylincom.isVisible = true
            status.isVisible = true
            commission.isVisible = true
        }
    }

    private fun ResellerForm()
    {
        tvCustomerP.setOnClickListener {
            etCompanyname.isVisible = false
            cylindername.isVisible = false
            companyaddress.isVisible = false
            companyphonenum.isVisible = false
            etUsername.isVisible = false
            lstname.isVisible = false
            memberaddress.isVisible = false
            memberphonenum.isVisible = false
            cylincom.isVisible = false
            status.isVisible = false
            commission.isVisible = false
            etResellerfullname.isVisible = true
            pasalname.isVisible = true
            reselleraddress.isVisible = true
            resellerphonenum.isVisible = true
        }
    }

    private fun CompanyForm()
    {
        tvCompanyP.setOnClickListener {
            etUsername.isVisible = false
            lstname.isVisible = false
            memberaddress.isVisible = false
            memberphonenum.isVisible = false
            cylincom.isVisible = false
            etResellerfullname.isVisible = false
            pasalname.isVisible = false
            reselleraddress.isVisible = false
            resellerphonenum.isVisible = false
            status.isVisible = false
            commission.isVisible = false
            etCompanyname.isVisible = true
            cylindername.isVisible = true
            companyaddress.isVisible = true
            companyphonenum.isVisible = true
        }
    }
}
