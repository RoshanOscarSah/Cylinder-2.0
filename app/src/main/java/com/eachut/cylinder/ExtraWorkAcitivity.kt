package com.eachut.cylinder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ExtraWorkAcitivity : AppCompatActivity() {

    private lateinit var ETDate : TextView
    private lateinit var TVtime : TextView
    private lateinit var Back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_work_acitivity)

        Back = findViewById(R.id.Back)
        ETDate = findViewById(R.id.ETDate)
        TVtime = findViewById(R.id.TVTime)

        Back.setOnClickListener {
            val intent = Intent(this, AddSchedule::class.java)
            startActivity(intent)
        }

        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val min = cal.get(Calendar.MINUTE)

        TVtime.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                TVtime.text = "Time : "+ hourOfDay+" : "+minute

            },hour, min, false)
            timePickerDialog.show()
        }


        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateTable(myCalendar)
        }
        ETDate.setOnClickListener{
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateTable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        ETDate.setText(sdf.format(myCalendar.time))
    }
}