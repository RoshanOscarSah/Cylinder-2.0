package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*

class ChangedefpassActivity : AppCompatActivity() {
    private lateinit var backChangedefpass: ImageView
    private lateinit var etdefUsername: EditText
    private lateinit var etdefPassword: EditText
    private lateinit var etnewPassword: EditText
    private lateinit var changepassbtn: TextView
    private lateinit var toggledefPasswordView: ToggleButton
    private lateinit var togglenewPasswordView: ToggleButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changedefpass)


        backChangedefpass = findViewById(R.id.backChangedefpass)
        etdefUsername = findViewById(R.id.etdefUsername)
        etdefPassword = findViewById(R.id.etdefPassword)
        etdefUsername = findViewById(R.id.etdefUsername)
        etnewPassword = findViewById(R.id.etnewPassword)
        changepassbtn = findViewById(R.id.changepassbtn)
        toggledefPasswordView = findViewById(R.id.toggledefPasswordView)
        togglenewPasswordView = findViewById(R.id.togglenewPasswordView)

        backChangedefpass.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        changepassbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        togglenewPasswordView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                togglenewPasswordView.setBackgroundResource(R.drawable.ic_eye_closed);
                etnewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // The toggle is disabled
                togglenewPasswordView.setBackgroundResource(R.drawable.ic_eye);
                etnewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

        toggledefPasswordView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                toggledefPasswordView.setBackgroundResource(R.drawable.ic_eye_closed);
                etdefPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // The toggle is disabled
                toggledefPasswordView.setBackgroundResource(R.drawable.ic_eye);
                etdefPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

    }
}