package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.fajarca.project.biometricauthentication.helper.toast

class SuccessMemberAdd : AppCompatActivity() {

    private lateinit var TVusername : TextView
    private lateinit var TVpassword : TextView
    private lateinit var OK : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_member_add)
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        Toast.makeText(this, "username: $username password: $password", Toast.LENGTH_SHORT).show()

        TVusername = findViewById(R.id.TVusername)
        TVpassword = findViewById(R.id.TVpassword)
        OK = findViewById(R.id.ok)

        TVusername.text = "$username"
        TVpassword.text = "$password"

        OK.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}