package com.eachut.cylinder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.eachut.cylinder.entity.ScheduleExtraWork
import com.eachut.cylinder.repository.ScheduleExtraWorkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class ExtraWorkAcitivity : AppCompatActivity() {

    private lateinit var etsubject : EditText
    private lateinit var etmessage : EditText
    private lateinit var ETDate : TextView
    private lateinit var TVtime : TextView
    private lateinit var Back : ImageView
    private lateinit var llGo : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_work_acitivity)

        etsubject = findViewById(R.id.etsubject)
        etmessage = findViewById(R.id.etmessage)
        Back = findViewById(R.id.Back)
        ETDate = findViewById(R.id.ETDate)
        TVtime = findViewById(R.id.TVTime)
        llGo = findViewById(R.id.llGo)

        Back.setOnClickListener {
            val intent = Intent(this, AddSchedule::class.java)
            startActivity(intent)
            finish()
        }

        llGo.setOnClickListener {
            addExtraWork()
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

    private fun addExtraWork()
    {
        val scheduledDate = ETDate.text.toString()
        val scheduledTime = TVtime.text.toString()
        val subject = etsubject.text.toString()
        val message = etmessage.text.toString()

        val scheduleExtraWork = ScheduleExtraWork(
            scheduledDate = scheduledDate,
            scheduledTime = scheduledTime,
            subject = subject,
            message = message,
            acceptedBy = "Anish"
        )
        Log.d("Anish", "ScheduleExtraWork : $scheduleExtraWork")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val scheduleExtraWorkRepository = ScheduleExtraWorkRepository()
                val response = scheduleExtraWorkRepository.addExtraWorkSchedule(scheduleExtraWork)

                Log.d("Anish", "Res: $response")
                if(response.success == true)
                {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ExtraWorkAcitivity,
                            "Extra Work Scheduled.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ExtraWorkAcitivity,
                            "Error when Scheduling.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            catch (ex: Exception)
            {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ExtraWorkAcitivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}