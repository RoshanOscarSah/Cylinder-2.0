package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide

class LoadingActivity : AppCompatActivity() {
    private lateinit var loadingLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingLogo = findViewById(R.id.loadingLogo)
        Glide.with(this)
            .load(R.drawable.fill_cylinder_animation)
            .into(loadingLogo);

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)


    }
}