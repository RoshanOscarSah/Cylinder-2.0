package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.eachut.cylinder.databinding.ActivityAddScheduleBinding
import com.eachut.cylinder.databinding.FragmentHomeBinding
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddSchedule : AppCompatActivity() {
    private lateinit var ivToggleBackground : ImageView
    private lateinit var ivToggleActive : ImageView
    private lateinit var tvCustomerOrCompany : TextView
    private lateinit var tvCustomer : TextView
    private lateinit var tvCompany : TextView
    private lateinit var llFullSelected : LinearLayout
    private lateinit var llHalfSelected : LinearLayout
    private lateinit var llEmptySelected : LinearLayout
    private lateinit var ivRlctoggleActive : ImageView
    private lateinit var tvRegular : TextView
    private lateinit var tvLeak : TextView
    private lateinit var tvSold : TextView
    private lateinit var tvGasTotal : TextView
    private lateinit var llRegular : LinearLayout
    private lateinit var llLeak : LinearLayout
    private lateinit var llSold : LinearLayout
    private lateinit var etGas1R : EditText
    private lateinit var etGas2R : EditText
    private lateinit var etGas3R : EditText
    private lateinit var etGas4R : EditText
    private lateinit var etGas1L : EditText
    private lateinit var etGas2L : EditText
    private lateinit var etGas3L : EditText
    private lateinit var etGas4L : EditText
    private lateinit var etGas1S : EditText
    private lateinit var etGas2S : EditText
    private lateinit var etGas3S : EditText
    private lateinit var etGas4S : EditText
    private lateinit var llStockSend : LinearLayout
    private lateinit var llStockReceive : LinearLayout
    private lateinit var ivRecieveFangro : ImageView
    private lateinit var ivSendFangro : ImageView
    private lateinit var llGo : LinearLayout
    private lateinit var ETDate : TextView
    private lateinit var TVtime : TextView
    private lateinit var Setting : Button
    private lateinit var llSelectReseller : LinearLayout
    private lateinit var llNameSelected : LinearLayout
    private var resellerList= mutableListOf<Reseller>()
    private var companyList= mutableListOf<Company>()
    private var gasState =  "Full"
    private var sendOrReceive = "Send"
    private var customerOrCompany = String()
    private var ResellerID = String()
    private var CompanyID = String()
    private lateinit var tvCompany : TextView
    private lateinit var ivToggleActive : ImageView
    private lateinit var tvCustomerOrCompany : TextView
    private lateinit var tvCustomer : TextView
    private var sendOrReceive = "Send"
    private lateinit var llStockSend : LinearLayout
    private lateinit var ivSendFangro : ImageView
    private lateinit var ivRecieveFangro : ImageView
    private lateinit var llStockReceive : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        ivToggleBackground = findViewById(R.id.ivToggleBackground)
        ivToggleActive = findViewById(R.id.ivToggleActive)
        tvCustomerOrCompany = findViewById(R.id.tvCustomerOrCompany)
        tvCustomer = findViewById(R.id.tvCustomer)
        tvCompany = findViewById(R.id.tvCompany)
        llFullSelected = findViewById(R.id.llFullSelected)
        llHalfSelected = findViewById(R.id.llHalfSelected)
        llEmptySelected = findViewById(R.id.llEmptySelected)
        ivRlctoggleActive = findViewById(R.id.ivRlctoggleActive)
        tvRegular = findViewById(R.id.tvRegular)
        tvLeak = findViewById(R.id.tvLeak)
        tvSold = findViewById(R.id.tvSold)
        tvGasTotal = findViewById(R.id.tvGasTotal)
        llRegular = findViewById(R.id.llRegular)
        llLeak = findViewById(R.id.llLeak)
        llSold = findViewById(R.id.llSold)
        etGas1R = findViewById(R.id.etGas1R)
        etGas2R = findViewById(R.id.etGas2R)
        etGas3R = findViewById(R.id.etGas3R)
        etGas4R = findViewById(R.id.etGas4R)
        etGas1L = findViewById(R.id.etGas1L)
        etGas2L = findViewById(R.id.etGas2L)
        etGas3L = findViewById(R.id.etGas3L)
        etGas4L = findViewById(R.id.etGas4L)
        etGas1S = findViewById(R.id.etGas1S)
        etGas2S = findViewById(R.id.etGas2S)
        etGas3S = findViewById(R.id.etGas3S)
        etGas4S = findViewById(R.id.etGas4S)
        llStockSend = findViewById(R.id.llStockSend)
        llStockReceive = findViewById(R.id.llStockReceive)
        ivRecieveFangro = findViewById(R.id.ivRecieveFangro)
        ivSendFangro = findViewById(R.id.ivSendFangro)
        llNameSelected = findViewById(R.id.llNameSelected)
        ivSendFangro = findViewById(R.id.ivSendFangro)
        llGo = findViewById(R.id.llGo)
        ETDate = findViewById(R.id.ETDate)
        TVtime = findViewById(R.id.TVTime)
        Setting = findViewById(R.id.setting)
        tvCompany = findViewById(R.id.tvCompany)
        ivToggleActive = findViewById(R.id.ivToggleActive)
        tvCustomerOrCompany = findViewById(R.id.tvCustomerOrCompany)
        tvCustomer = findViewById(R.id.tvCustomer)
        llStockSend = findViewById(R.id.llStockSend)
        ivSendFangro = findViewById(R.id.ivSendFangro)
        ivRecieveFangro = findViewById(R.id.ivRecieveFangro)
        llStockReceive = findViewById(R.id.llStockReceive)





//Company reseller selection
        tvCompany.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivToggleActive, "translationX", 460f).apply {
//                duration = 200
//                start()
//            }
            tvCustomerOrCompany.setText("Select Company")
            tvCustomerOrCompany.setContentDescription("getCompany")
        }


        tvCompany.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivToggleActive, "translationX", 460f).apply {
//                duration = 200
//                start()
//            }
            tvCustomerOrCompany.setText("Select Company")
            tvCustomerOrCompany.setContentDescription("getCompany")
        }
        tvCustomer.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivToggleActive, "translationX", 0f).apply {
//                duration = 200
//                start()
//            }
            tvCustomerOrCompany.setText("Select Reseller")
            tvCustomerOrCompany.setContentDescription("getReseller")
        }



//Back to Notification fragment
        val backButton = findViewById<View>(R.id.Back) as ImageView
        backButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })


//Extra Work Activity
        Setting.setOnClickListener {
            val intent = Intent(this, ExtraWorkAcitivity::class.java)
            startActivity(intent)
        }

        //Time PIcker

        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val min = cal.get(Calendar.MINUTE)

        TVtime.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                TVtime.text = "Time : "+ hourOfDay+" : "+minute

            },hour, min, false)
            timePickerDialog.show()
        }

        //Date Picker


        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateTable(myCalendar)
        }
        ETDate.setOnClickListener{
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateTable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        ETDate.setText(sdf.format(myCalendar.time))
    }
}