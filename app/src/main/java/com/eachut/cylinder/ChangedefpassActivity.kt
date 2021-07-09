package com.eachut.cylinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import com.eachut.cylinder.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

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
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ChangedefpassActivity,"Clicked" , Toast.LENGTH_SHORT).show()
                    }
                    val username = etdefUsername.text.toString()
                    val password = etdefPassword.text.toString()
                    val new_password = etnewPassword.text.toString()
                    val userRepository = UserRepository()
                    val userResponse = userRepository.changePassword(username, password, new_password)
                    if(userResponse.success==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@ChangedefpassActivity,"password changed" , Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@ChangedefpassActivity,userResponse.message.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ChangedefpassActivity,"$e" , Toast.LENGTH_SHORT).show()
                    }
                }
            }

//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
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