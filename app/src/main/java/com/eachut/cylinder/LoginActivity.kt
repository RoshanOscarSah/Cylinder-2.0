package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.transition.TransitionManager
import com.eachut.cylinder.repository.MemberRepository
import com.google.android.material.snackbar.Snackbar
import io.fajarca.project.biometricauthentication.helper.AuthenticationError
import io.fajarca.project.biometricauthentication.helper.navigateTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var biometricManager: BiometricManager

    private lateinit var loginbtn: TextView
    private lateinit var etUsername: TextView
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
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        togglePasswordView = findViewById(R.id.togglePasswordView)
        fingerReader = findViewById(R.id.fingerReader)
        setting = findViewById(R.id.setting)
        animateFallDiagnol = findViewById(R.id.animateFallDiagnol)
        root_layout = findViewById(R.id.root_layout)


        setupBiometricAuthentication()
        checkBiometricFeatureState()

        fingerReader.setOnClickListener {
            val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
            val TouchID = sharedPreferences.getString("TouchID", "")
            if (TouchID == "true") {
                if (isBiometricFeatureAvailable()) {
                    biometricPrompt.authenticate(buildBiometricPrompt())

                } else {
                    Snackbar.make(fingerReader, "Sensor Not Found!", Snackbar.LENGTH_SHORT).show()
                }
            } else if (TouchID == "false") {
                Snackbar.make(
                    fingerReader,
                    "Touch Id can be enabled from setting",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    fingerReader,
                    "Fingerprint not implimented yet! Try login",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }


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
            popupWindow.setWidth(width - 50);
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
            val touchIdSwitch = view.findViewById<Switch>(R.id.touchIdSwitch)


            //            loadLocate
            val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
            val language = sharedPreferences.getString("My_Lang", "")
            Log.d("OSCAR", "L : $language")
            if (language == "ne") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d("OSCAR", "L a")
                    english1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.notselectedLanguage)))
                    nepali1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.selectedLanguage)))
                };
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d("OSCAR", "L b")
                    english1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.selectedLanguage)))
                    nepali1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.notselectedLanguage)))
                };
            }

            //touch id
            val TouchID = sharedPreferences.getString("TouchID", "")
            if (TouchID == "true") {
                touchIdSwitch.setChecked(true)
            } else if (TouchID == "false") {
                touchIdSwitch.setChecked(false)
            } else {
                touchIdSwitch.setChecked(false)
            }

            touchIdSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                // The toggle is enabled
                    val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                    editor.putString("TouchID","")
                    editor.putString("Username","")
                    editor.putString("Password","")
                    editor.apply()

                    Toast.makeText(
                        this@LoginActivity,
                        "Touch Id Reset, Please login Now",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                       // The toggle is disabled
                    val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                    editor.putString("TouchID","false")
                    editor.putString("Username","")
                    editor.putString("Password","")
                    editor.apply()

                }
            }

//                // Set a click listener for popup's button widget
            changePassword.setOnClickListener {
                val intent = Intent(this, ChangedefpassActivity::class.java)
                startActivity(intent)
            }

            english1.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d("OSCAR", "L 1")
                    english1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.selectedLanguage)))
                    nepali1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.notselectedLanguage)))
                    val locale = Locale("en")
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    baseContext.resources.updateConfiguration(
                        config,
                        baseContext.resources.displayMetrics
                    )
                    val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                    editor.putString("My_Lang", "en")
                    editor.apply()
                    recreate()
                };
            }
            nepali1.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d("OSCAR", "L 2")
                    english1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.notselectedLanguage)))
                    nepali1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.selectedLanguage)))
                    val locale = Locale("ne")
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    baseContext.resources.updateConfiguration(
                        config,
                        baseContext.resources.displayMetrics
                    )
                    val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                    editor.putString("My_Lang", "ne")
                    editor.apply()
                    recreate()
                };
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


        loginbtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val Username = etUsername.text.toString()
                    val Password = etPassword.text.toString()
                    val memberRepository = MemberRepository()
                    val memberResponse = memberRepository.checkMember(Username, Password)
                    if (memberResponse.success == true) {
                        val sharedPreferences =
                            getSharedPreferences("Settings", Activity.MODE_PRIVATE)
                        val TouchID = sharedPreferences.getString("TouchID", "")
                        Log.d("OSCAR", "TouchID : $TouchID")
                        if (TouchID == "true") {
                            if (memberResponse.status == "Admin") {
                                val editor =
                                    getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                                editor.putString("Admin", "true")
                                editor.apply()

                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Welcome Admin, You can also use Touch id too!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        LoadingActivity::class.java
                                    )
                                )
                            } else if (memberResponse.status == "Employee") {
                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Welcome Employee, You can also use Touch id too",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                val editor =
                                    getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                                editor.putString("Admin", "false")
                                editor.apply()

                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        LoadingActivity::class.java
                                    )
                                )
                            } else {
                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Unauthorized ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else if (TouchID == "false") {
                            if (memberResponse.status == "Admin") {
                                val editor =
                                    getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                                editor.putString("Admin", "true")
                                editor.apply()

                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Welcome Admin!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        LoadingActivity::class.java
                                    )
                                )
                            } else if (memberResponse.status == "Employee") {
                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Welcome Employee",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                val editor =
                                    getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                                editor.putString("Admin", "false")
                                editor.apply()

                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        LoadingActivity::class.java
                                    )
                                )
                            } else {
                                withContext(Main) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Unauthorized ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Log.d("OSCAR", "TouchID")

                            withContext(Main) {
                                showDialog(Username,Password)
                            }
                        }

                    } else if (memberResponse.success == false) {
                        withContext(Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "message : ${memberResponse.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        withContext(Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Error : Login unsuccessful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                //need to be checked
                catch (e: Exception) {
                    withContext(Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Unauthorized Member: $e",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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

    private fun setupBiometricAuthentication() {
        biometricManager = BiometricManager.from(this)
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, biometricCallback)
    }

    private fun checkBiometricFeatureState() {
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> setErrorNotice("Sorry. It seems your device has no biometric hardware")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> setErrorNotice("Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> setErrorNotice("You have not registered any biometric credentials")
            BiometricManager.BIOMETRIC_SUCCESS -> {
            }
        }
    }

    private fun buildBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify your identity")
            .setDescription("Confirm your identity so we can verify it's you")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(false) //Allows user to authenticate without performing an action, such as pressing a button, after their biometric credential is accepted.
            .build()
    }

    private fun isBiometricFeatureAvailable(): Boolean {
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    private val biometricCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
//            navigateTo<LoadingActivity>()
                TouchIDCheck()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)

            if (errorCode != AuthenticationError.AUTHENTICATION_DIALOG_DISMISSED.errorCode && errorCode != AuthenticationError.CANCELLED.errorCode) {
                setErrorNotice(errString.toString())
            }
        }
    }

    private fun setErrorNotice(errorMessage: String) {

    }

    // to show popup
    fun showDialog(Username:String,Password:String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Enable Touch ID")
        dialogBuilder.setPositiveButton("Okay",
            DialogInterface.OnClickListener { dialog, whichButton ->
                val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                editor.putString("TouchID","true")
                editor.putString("Username",Username)
                editor.putString("Password",Password)
                editor.apply()

                Snackbar.make(fingerReader, "Use FingerPrint to Login", Snackbar.LENGTH_SHORT).show()
            })
        dialogBuilder.setNegativeButton("Cancal",
            DialogInterface.OnClickListener { dialog, whichButton ->
                val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                editor.putString("TouchID","false")
                editor.apply()
                dialog.dismiss()

                val intent = Intent(this, LoadingActivity::class.java)
                startActivity(intent)
            })
        val b = dialogBuilder.create()
        b.show()
    }

    fun TouchIDCheck() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val Username = sharedPreferences.getString("Username", "")
        val Password = sharedPreferences.getString("Password", "")
        Log.d("OSCAR", "U,P : $Username , $Password")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val Username = Username.toString()
                val Password = Password.toString()
                val memberRepository = MemberRepository()
                val memberResponse = memberRepository.checkMember(Username, Password)
                if (memberResponse.success == true) {
                    val sharedPreferences =
                        getSharedPreferences("Settings", Activity.MODE_PRIVATE)
                    val TouchID = sharedPreferences.getString("TouchID", "")
                    Log.d("OSCAR", "TouchID : $TouchID")
                    if (TouchID == "true") {
                        if (memberResponse.status == "Admin") {
                            val editor =
                                getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                            editor.putString("Admin", "true")
                            editor.apply()


                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome Admin!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    LoadingActivity::class.java
                                )
                            )
                        } else if (memberResponse.status == "Employee") {
                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome Employee",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            val editor =
                                getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                            editor.putString("Admin", "false")
                            editor.apply()

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    LoadingActivity::class.java
                                )
                            )
                        } else {
                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Unauthorized ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else if (TouchID == "false") {
                        if (memberResponse.status == "Admin") {
                            val editor =
                                getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                            editor.putString("Admin", "true")
                            editor.apply()

                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome Admin!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    LoadingActivity::class.java
                                )
                            )
                        } else if (memberResponse.status == "Employee") {
                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome Employee",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            val editor =
                                getSharedPreferences("Admin", Context.MODE_PRIVATE).edit()
                            editor.putString("Admin", "false")
                            editor.apply()

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    LoadingActivity::class.java
                                )
                            )
                        } else {
                            withContext(Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Unauthorized ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Log.d("OSCAR", "TouchID")

                        withContext(Main) {
                            showDialog(Username,Password)
                        }
                    }

                } else if (memberResponse.success == false) {
                    withContext(Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "message : ${memberResponse.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    withContext(Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error : Login unsuccessful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            //need to be checked
            catch (e: Exception) {
                withContext(Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Unauthorized Member: $e",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }



}
