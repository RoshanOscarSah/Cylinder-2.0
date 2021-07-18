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
import com.eachut.cylinder.entity.Member
import com.eachut.cylinder.repository.MemberRepository
import kotlinx.coroutines.*

class AddNewMemberActivity : AppCompatActivity() {


    private lateinit var etMFname: EditText
    private lateinit var etMLname: EditText
    private lateinit var etMPhone: EditText
    private lateinit var etMAddress: EditText
    private lateinit var cylincom: EditText
    private lateinit var btnAddProfile: TextView
    private lateinit var ivSendCheck: ImageView
    private lateinit var ivRecieveCheck: ImageView
    private lateinit var llAdmin: LinearLayout
    private lateinit var llEmploye: LinearLayout
    private lateinit var adminOrEmployee: LinearLayout
    private lateinit var commission: LinearLayout


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_member)

        etMFname = findViewById(R.id.etMFname)
        etMLname = findViewById(R.id.etMLname)
        etMPhone = findViewById(R.id.etMPhone)
        etMAddress = findViewById(R.id.etMAddress)
        cylincom = findViewById(R.id.cylincom)
        btnAddProfile = findViewById(R.id.btnAddProfile)
        ivSendCheck = findViewById(R.id.ivSendCheck)
        ivRecieveCheck = findViewById(R.id.ivRecieveCheck)
        llAdmin = findViewById(R.id.llAdmin)
        llEmploye = findViewById(R.id.llEmploye)
        adminOrEmployee = findViewById(R.id.adminOrEmployee)
        commission = findViewById(R.id.commission)


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

            adminOrEmployee.setContentDescription("ADMIN")


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

            adminOrEmployee.setContentDescription("EMPLOYEE")

            llEmploye.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive)));
            llAdmin.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.sendreceive_fade)));

        }
        btnAddProfile.setOnClickListener {

            val etMFname = etMFname.text.toString()
            val etMLname = etMLname.text.toString()
            val etMPhone = etMPhone.text.toString()
            val etMAddress = etMAddress.text.toString()
            var cylincom = cylincom.text.toString()
            val status = adminOrEmployee.getContentDescription().toString()

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
    }
}