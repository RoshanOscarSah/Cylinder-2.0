package com.eachut.cylinder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val welcomeOrNot = sharedPreferences.getString("welcomeOrNot","true")

        if(welcomeOrNot == "true"){
            Handler().postDelayed({
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }else if(welcomeOrNot == "false"){
            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }else{
            Toast.makeText(this@SplashActivity,"Error : splash" , Toast.LENGTH_SHORT).show()
        }



    }
}