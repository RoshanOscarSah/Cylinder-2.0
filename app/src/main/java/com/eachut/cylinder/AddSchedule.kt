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
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddSchedule : AppCompatActivity() {

    private lateinit var ETDate : TextView
    private lateinit var TVtime : TextView
    private lateinit var Setting : Button
    private lateinit var llSelectCustomer : LinearLayout
    private lateinit var llNameSelected : LinearLayout
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

        llSelectCustomer = findViewById(R.id.llSelectReseller)
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