package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class WelcomeActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var fadein: ImageView
    private lateinit var english: Button
    private lateinit var nepali: Button
    private lateinit var welcome: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        nepali.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}