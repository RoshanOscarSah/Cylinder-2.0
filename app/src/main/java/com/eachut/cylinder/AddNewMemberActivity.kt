package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.MemberRepository
import com.eachut.cylinder.repository.ResellerRepository
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



    private lateinit var tvMemberP:TextView
    private lateinit var tvCompanyP:TextView
    private lateinit var tvCustomerP:TextView

    private lateinit var status:LinearLayout


    private lateinit var etMFname: EditText
    private lateinit var etMLname: EditText
    private lateinit var etMPhone: EditText
    private lateinit var etMAddress: EditText
    private lateinit var cylincom: EditText
    private lateinit var btnAddProfile: LinearLayout
    private lateinit var ivSendCheck: ImageView
    private lateinit var ivRecieveCheck: ImageView
    private lateinit var llAdmin: LinearLayout
    private lateinit var llEmploye: LinearLayout
    private lateinit var commission: LinearLayout

    private lateinit var btnAddCompany : LinearLayout
    private lateinit var btnAddReseller : LinearLayout


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        btnAddProfile = findViewById(R.id.btnAddProfile)

        tvMemberP = findViewById(R.id.tvMemberP)
        tvCompanyP = findViewById(R.id.tvCompanyP)
        tvCustomerP = findViewById(R.id.tvCustomerP)

        status = findViewById(R.id.status)
        ivSendCheck = findViewById(R.id.ivSendCheck)
        ivRecieveCheck = findViewById(R.id.ivRecieveCheck)
        llAdmin = findViewById(R.id.llAdmin)
        llEmploye = findViewById(R.id.llemploye)

        btnAddCompany = findViewById(R.id.btnAddCompany)
        btnAddReseller = findViewById(R.id.btnAddReseller)

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
        btnAddReseller.isVisible = false
        btnAddCompany.isVisible = false
        btnAddProfile.isVisible = true

        MemberForm()
        CompanyForm()
        ResellerForm()



//Admin / Employee
        llAdmin.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(ivSendCheck, "translationX", 0f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(ivRecieveCheck, "translationX", -250f).apply {
                duration = 200
                start()
            }



            ivRecieveCheck.isVisible = false
            ivSendCheck.isVisible = true
            commission.isVisible = false

            status.setContentDescription("ADMIN")


            llEmploye.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive_fade)));
            llAdmin.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive)));
        }

        llEmploye.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(ivSendCheck, "translationX", 460f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(ivRecieveCheck, "translationX", 210f).apply {
                duration = 200
                start()
            }

            ivRecieveCheck.isVisible = true
            ivSendCheck.isVisible = false
            commission.isVisible = true

            status.setContentDescription("EMPLOYEE")

            llEmploye.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive)));
            llAdmin.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive_fade)));

        }
        //add reseller

        btnAddReseller.setOnClickListener {
            val etResellerfullname = etResellerfullname.text.toString()
            val pasalname = pasalname.text.toString()
            val reselleraddress = reselleraddress.text.toString()
            val resellerphonenum = resellerphonenum.text.toString()
            val reseller = Reseller(
                reseller_fullname = etResellerfullname,
                pasal_name = pasalname,
                address = reselleraddress,
                phone_number = resellerphonenum
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val resellerRepository = ResellerRepository()
                    val response = resellerRepository.addNewReseller(reseller)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Reseller Added Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Reseller Not Added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddNewMemberActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        //add Member
        btnAddProfile.setOnClickListener {

            val etMFname = etMFname.text.toString()
            val etMLname = etMLname.text.toString()
            val etMPhone = etMPhone.text.toString()
            val etMAddress = etMAddress.text.toString()
            var cylincom = cylincom.text.toString()
            val status = status.getContentDescription().toString()

                if (status=="ADMIN"){
                    cylincom = "0"
                }
            val member = Member(
                Firstname = etMFname,
                Lastname = etMLname,
                Status = status,
                Phonenumber = etMPhone,
                Address = etMAddress,
                Comission = cylincom
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val memberRepository = MemberRepository()
                    val response = memberRepository.addnewmemberadmin(member)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Member added  Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Member Not Added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddNewMemberActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }

        //add company
        btnAddCompany.setOnClickListener {
            val etCompanyname = etCompanyname.text.toString()
            val cylindername = cylindername.text.toString()
            val companyaddress = companyaddress.text.toString()
            val companyphonenum = companyphonenum.text.toString()
            val company = Company(
                company_fullname = etCompanyname,
                cylinder_name = cylindername,
                address = companyaddress,
                phone_number = companyphonenum
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val companyRepository = CompanyRepository()
                    val response = companyRepository.newCompany(company)
                    if (response.success == true){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Company Added Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else{
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Company Not Added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddNewMemberActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
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
            btnAddReseller.isVisible = false
            btnAddCompany.isVisible = false
            btnAddProfile.isVisible = true
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
            btnAddReseller.isVisible = true
            btnAddCompany.isVisible = false
            btnAddProfile.isVisible = false
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
            btnAddReseller.isVisible = false
            btnAddCompany.isVisible = true
            btnAddProfile.isVisible = false
        }
    }
}