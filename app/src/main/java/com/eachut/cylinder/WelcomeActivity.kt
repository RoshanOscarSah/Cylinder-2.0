package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class WelcomeActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var fadein: ImageView
    private lateinit var english: Button
    private lateinit var nepali: Button
    private lateinit var welcome: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_welcome)

        image = findViewById(R.id.splashLogo)
        welcome = findViewById(R.id.welcome)
        fadein = findViewById(R.id.fadein)
        english = findViewById(R.id.english)
        nepali = findViewById(R.id.nepali)

        //animation 360 degree rotation
//        val animation = ObjectAnimator.ofFloat(image, "rotationY", 0.0f, 360f)
//        animation.duration = 3600
//        animation.repeatCount = ObjectAnimator.INFINITE
//        animation.interpolator = AccelerateDecelerateInterpolator()
//        animation.start()

        ObjectAnimator.ofFloat(fadein, "translationY", -440f).apply {
            duration = 800
            start()
        }

        ObjectAnimator.ofFloat(image, "translationY", -350f).apply {
            duration = 800
            start()
        }

        ObjectAnimator.ofFloat(welcome, "translationY", -120f).apply {
            duration = 1000
            start()
        }

        english.setOnClickListener {
            val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
            editor.putString("welcomeOrNot","false")
            editor.apply()

            setLocate("en")
            recreate()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        nepali.setOnClickListener {
            val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
            editor.putString("welcomeOrNot","false")
            editor.apply()

            setLocate("ne")
            recreate()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


    private fun setLocate (Lang: String?){
        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }
    private fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang","")
        setLocate(language)
    }
}