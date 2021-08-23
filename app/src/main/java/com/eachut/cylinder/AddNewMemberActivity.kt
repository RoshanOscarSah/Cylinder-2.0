package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.MemberRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.*

class AddNewMemberActivity : AppCompatActivity() {

    private lateinit var ivToggleActiveP : ImageView

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
    private lateinit var btnAddMember: LinearLayout
    private lateinit var ivSendCheck: ImageView
    private lateinit var ivRecieveCheck: ImageView
    private lateinit var llAdmin: LinearLayout
    private lateinit var llEmploye: LinearLayout
    private lateinit var commission: LinearLayout
    private lateinit var etresellerRate: EditText
    private lateinit var resellerRate: LinearLayout

    private lateinit var btnAddCompany : LinearLayout
    private lateinit var btnAddReseller : LinearLayout


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_member)

        ivToggleActiveP = findViewById(R.id.ivToggleActiveP)

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
        btnAddMember = findViewById(R.id.btnAddMember)

        tvMemberP = findViewById(R.id.tvMemberP)
        tvCompanyP = findViewById(R.id.tvCompanyP)
        tvCustomerP = findViewById(R.id.tvCustomerP)

        status = findViewById(R.id.status)
        ivSendCheck = findViewById(R.id.ivSendCheck)
        ivRecieveCheck = findViewById(R.id.ivRecieveCheck)
        llAdmin = findViewById(R.id.llAdmin)
        llEmploye = findViewById(R.id.llemploye)
        resellerRate = findViewById(R.id.resellerRate)
        etresellerRate = findViewById(R.id.etresellerRate)

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
        btnAddMember.isVisible = true

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

            status.setContentDescription("Admin")


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

            status.setContentDescription("Employee")

            llEmploye.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive)));
            llAdmin.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive_fade)));

        }

        val backButton = findViewById<View>(R.id.backAddPro) as LinearLayout
        backButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })



        //add reseller
        btnAddReseller.setOnClickListener {
            val etResellerfullname = etResellerfullname.text.toString()
            val pasalname = pasalname.text.toString()
            val reselleraddress = reselleraddress.text.toString()
            val resellerphonenum = resellerphonenum.text.toString()
            val rateforReseller = etresellerRate.text.toString()
            val reseller = Reseller(
                reseller_fullname = etResellerfullname,
                pasal_name = pasalname,
                address = reselleraddress,
                phone_number = resellerphonenum,
                rateforReseller = rateforReseller
            )
            Log.d("OSCAR", "Reseller: $reseller")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val resellerRepository = ResellerRepository()
                    val response = resellerRepository.addNewReseller(reseller)
                    Log.d("OSCAR", "Res: $response")
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
        btnAddMember.setOnClickListener {


            val etUsername = etUsername.text.toString()
            val lstname = lstname.text.toString()
            val memberphonenum = memberphonenum.text.toString()
            val memberaddress = memberaddress.text.toString()
            var cylincom = cylincom.text.toString()
            val status = status.getContentDescription().toString()

                if (status=="Admin"){
                    cylincom = "0"
                }

            val member = Member(
                Firstname = etUsername,
                Lastname = lstname,
                Status = status,
                Phonenumber = memberphonenum,
                Address = memberaddress,
                Comission = cylincom,
            )
            Log.d("OSCAR", "Member : $member")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val memberRepository = MemberRepository()
                    val response = memberRepository.addnewmemberadmin(member)
                    Log.d("OSCAR", "Member : try")
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Member added  Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val username = response.username.toString()
                            val password = response.password.toString()
                            val intent = Intent(this@AddNewMemberActivity,SuccessMemberAdd::class.java)
                                .putExtra("username", username)
                                .putExtra("password", password)
                            startActivity(intent)
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
                }
                catch (ex: Exception) {
                    Log.d("OSCAR", "Member : catch $ex")
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

            Log.d("OSCAR", "Company: $company")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val companyRepository = CompanyRepository()
                    val response = companyRepository.newCompany(company)

                    Log.d("OSCAR", "Comp resp: $response")
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

            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            ivToggleActiveP.setLayoutParams(params);

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
            commission.isVisible = false
            btnAddReseller.isVisible = false
            btnAddCompany.isVisible = false
            btnAddMember.isVisible = true
            resellerRate.isVisible = false
        }
    }

    private fun ResellerForm()
    {
        tvCustomerP.setOnClickListener {
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            ivToggleActiveP.setLayoutParams(params);

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
            btnAddMember.isVisible = false
            resellerRate.isVisible = true
        }
    }

    private fun CompanyForm()
    {
        tvCompanyP.setOnClickListener {
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            ivToggleActiveP.setLayoutParams(params);

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
            btnAddMember.isVisible = false
            resellerRate.isVisible = false
        }
    }
}