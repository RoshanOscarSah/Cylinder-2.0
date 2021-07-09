package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.eachut.cylinder.entity.User
import com.eachut.cylinder.repository.UserRepository
import kotlinx.coroutines.*
import org.w3c.dom.Text

class AddNewMemberActivity : AppCompatActivity() {

    private lateinit var etUsername : TextView
    private lateinit var lstname : TextView
    private lateinit var phonenum : TextView
    private lateinit var address : TextView
    private lateinit var cylincom : TextView
    private lateinit var loginbtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_member)

        etUsername = findViewById(R.id.etUsername)
        lstname = findViewById(R.id.lstname)
        phonenum = findViewById(R.id.phonenum)
        address = findViewById(R.id.address)
        cylincom = findViewById(R.id.cylincom)
        loginbtn = findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener {

            val first_name = etUsername.text.toString()
            val last_name = lstname.text.toString()
            val phone_number = phonenum.text.toString()
            val address = address.text.toString()
            val comission_percent = cylincom.text.toString()

            val user = User(first_name = first_name, last_name = last_name,phone_number = phone_number,address = address,comission_percent = comission_percent)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.addnewmemberadmin(user)
                    if (response.success == true){
                        startActivity(Intent(this@AddNewMemberActivity,AddNewMemberActivity::class.java))
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddNewMemberActivity,
                                "Member Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else{
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
                        Toast.makeText(this@AddNewMemberActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT).show()
                    }
                }
                }
            }

        }
    }
