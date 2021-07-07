package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var loginbtn: TextView
    private lateinit var etPassword: EditText
    private lateinit var togglePasswordView: ToggleButton
    private lateinit var fingerReader: ImageView
    private lateinit var setting: ToggleButton
    private lateinit var animateFallDiagnol: LinearLayout
    private lateinit var root_layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginbtn = findViewById(R.id.loginbtn)
        etPassword = findViewById(R.id.etPassword)
        togglePasswordView = findViewById(R.id.togglePasswordView)
        fingerReader = findViewById(R.id.fingerReader)
        setting = findViewById(R.id.setting)
        animateFallDiagnol = findViewById(R.id.animateFallDiagnol)
        root_layout = findViewById(R.id.root_layout)



//        val animSetXY = AnimatorSet()
//
//        val y: ObjectAnimator = ObjectAnimator.ofFloat(
//            animateFallDiagnol,
//            "translationY", animateFallDiagnol.getY(), 1200f
//        )
//
//        val x: ObjectAnimator = ObjectAnimator.ofFloat(
//            animateFallDiagnol,
//            "translationX", animateFallDiagnol.getX(), -500f
//        )
//
//        animSetXY.playTogether(x, y)
//        animSetXY.interpolator = AccelerateDecelerateInterpolator()
//        animSetXY.duration = 500
//        animSetXY.start()

        setting.setOnCheckedChangeListener { _, isChecked ->

//            animation
            val animation = ObjectAnimator.ofFloat(setting, "rotation", 0f, 180f)
            animation.duration = 500
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()

            //starting popup
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width: Int = size.x

            // Initialize a new layout inflater instance
            val inflater: LayoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.activity_presetting, null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
                1300, // Window height
            )
            popupWindow.setWidth(width-50);
            popupWindow.setFocusable(true);

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // If API level 23 or higher then execute the code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.TOP
                popupWindow.exitTransition = slideOut

            }

            // Get the widgets reference from custom view
            val changePassword = view.findViewById<TextView>(R.id.changePassword)
            val nepali1 = view.findViewById<Button>(R.id.nepali1)
            val english1 = view.findViewById<Button>(R.id.english1)
//



//                // Set a click listener for popup's button widget
            changePassword.setOnClickListener {
                val intent = Intent(this, ChangedefpassActivity::class.java)
                startActivity(intent)
            }



            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.TOP, // Exact position of layout to display popup
                0, // X offset
                350 // Y offset
            )



        }


        loginbtn.setOnClickListener{
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
        }

        fingerReader.setOnClickListener {
            Snackbar.make(fingerReader, "Fingerprint not implimented yet!", Snackbar.LENGTH_SHORT).show()
        }

        togglePasswordView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                togglePasswordView.setBackgroundResource(R.drawable.ic_eye_closed);
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // The toggle is disabled
                togglePasswordView.setBackgroundResource(R.drawable.ic_eye);
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }





    }

    // to show popup
    fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("The message here")
        dialogBuilder.setPositiveButton("Done",
            DialogInterface.OnClickListener { dialog, whichButton -> })
        val b = dialogBuilder.create()
        b.show()
    }
}