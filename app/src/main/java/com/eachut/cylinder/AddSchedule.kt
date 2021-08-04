package com.eachut.cylinder

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.CompanyStockViewAdapter
import com.eachut.cylinder.Adapter.ResellerStockViewAdapter
import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.Object.ResellerDetails
import com.eachut.cylinder.Object.ResellerStockDetails
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import com.eachut.cylinder.repository.ScheduleResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
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
    private lateinit var llFullSelectedImg : ImageView
    private lateinit var llFullSelectedTxt : TextView
    private lateinit var llHalfSelected : LinearLayout
    private lateinit var llHalfSelectedImg : ImageView
    private lateinit var llHalfSelectedTxt : TextView
    private lateinit var llEmptySelected : LinearLayout
    private lateinit var llEmptySelectedImg : ImageView
    private lateinit var llEmptySelectedTxt : TextView
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
    private lateinit var tvGasTotalR : TextView
    private lateinit var tvGasTotalL : TextView
    private lateinit var tvGasTotalS : TextView
    private lateinit var llStockSend : LinearLayout
    private lateinit var llStockReceive : LinearLayout
    private lateinit var ivRecieveFangro : ImageView
    private lateinit var ivSendFangro : ImageView
    private lateinit var llGo : LinearLayout
    private lateinit var ETDate : TextView
    private lateinit var TVTime : TextView
    private lateinit var etremarks : EditText
    private lateinit var Setting : Button
    private lateinit var llSelectReseller : LinearLayout
    private lateinit var llNameSelected : LinearLayout
    private var resellerList= mutableListOf<Reseller>()
    private var companyList= mutableListOf<Company>()
    private var gasState =  "Full"
    private var sendOrReceive = "Send"
    private var customerOrCompany = String()

    private lateinit var title:TextView
    private lateinit var subtitle:TextView
    private lateinit var address:TextView
    private lateinit var tvDashboardRate:TextView
    private lateinit var tvDashboardHalfcylinder:TextView
    private lateinit var tvDashboardLeakcylinder:TextView
    private lateinit var tvDashboardTag:TextView
    private lateinit var tvDashboardBurn:TextView
    private lateinit var tvDashboardCylinder:TextView
    private lateinit var ivCall:LinearLayout
    private var ResellerID = String()
    private var CompanyID = String()

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
        llFullSelectedImg = findViewById(R.id.llFullSelectedImg)
        llHalfSelectedImg = findViewById(R.id.llHalfSelectedImg)
        llEmptySelectedImg = findViewById(R.id.llEmptySelectedImg)
        llFullSelectedTxt = findViewById(R.id.llFullSelectedTxt)
        llHalfSelectedTxt = findViewById(R.id.llHalfSelectedTxt)
        llEmptySelectedTxt = findViewById(R.id.llEmptySelectedTxt)
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
        tvGasTotalR = findViewById(R.id.tvGasTotalR)
        tvGasTotalL = findViewById(R.id.tvGasTotalL)
        tvGasTotalS = findViewById(R.id.tvGasTotalS)
        llStockSend = findViewById(R.id.llStockSend)
        llStockReceive = findViewById(R.id.llStockReceive)
        ivRecieveFangro = findViewById(R.id.ivRecieveFangro)
        ivSendFangro = findViewById(R.id.ivSendFangro)
        llNameSelected = findViewById(R.id.llNameSelected)
        ivSendFangro = findViewById(R.id.ivSendFangro)
        llGo = findViewById(R.id.llGo)
        ETDate = findViewById(R.id.ETDate)
        TVTime = findViewById(R.id.TVTime)
        etremarks = findViewById(R.id.etremarks)
        Setting = findViewById(R.id.setting)
        title = findViewById(R.id.title)
        subtitle = findViewById(R.id.subtitle)
        address = findViewById(R.id.address)
        ivCall = findViewById(R.id.ivCall)
        tvDashboardRate = findViewById(R.id.tv_dashboard_rate)
        tvDashboardHalfcylinder = findViewById(R.id.tv_dashboard_halfcylinder)
        tvDashboardLeakcylinder = findViewById(R.id.tv_dashboard_leakcylinder)
        tvDashboardTag = findViewById(R.id.tv_dashboard_tag)
        tvDashboardBurn = findViewById(R.id.tv_dashboard_burn)
        tvDashboardCylinder = findViewById(R.id.tv_dashboard_cylinder)




//Company reseller selection
        tvCompany.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(ivToggleActive, "translationX", 460f).apply {
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
//            ObjectAnimator.ofFloat(ivToggleActive, "translationX", 0f).apply {
//                duration = 200
//                start()
//            }
            tvCustomerOrCompany.setText("Select Reseller")
            tvCustomerOrCompany.setContentDescription("getReseller")
        }

        //select customer
        llSelectReseller.setOnClickListener { view ->
            customerOrCompany = tvCustomerOrCompany.getContentDescription().toString()
            llNameSelected.isVisible = true

            if (customerOrCompany == "getReseller"){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val resellerRepository = ResellerRepository()
                        val resellerResponse  =resellerRepository.allresellerList()
                        if(resellerResponse.success!!){
                            resellerList = resellerResponse.data!!

                        }
                        withContext(Dispatchers.Main){
                            showPopupReseller()
//                            Toast.makeText(context, "Get Reseller", Toast.LENGTH_SHORT).show()
                        }
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@AddSchedule, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
            if(customerOrCompany == "getCompany"){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val companyRepository = CompanyRepository()
                        val companyResponse  =companyRepository.allCompanyList()
                        if(companyResponse.success!!){
                            companyList = companyResponse.data!!
                        }
                        withContext(Dispatchers.Main){
                            showPopupCompany()
//                            Toast.makeText(context, "Get Company", Toast.LENGTH_SHORT).show()
                        }
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@AddSchedule, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            }

        }

        //Full HALF EMPTY CYLINDER
        llFullSelected.setOnClickListener{ view ->
//        changing image
            llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full)
            llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half_fade)
            llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_fade)
//        changing text opacity
            llFullSelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            llHalfSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            llEmptySelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            gasState = "Full"
        }

        llHalfSelected.setOnClickListener{ view ->
//        changing image
            llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full_fade)
            llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half)
            llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_fade)
//        changing text opacity
            llFullSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            llHalfSelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            llEmptySelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            gasState = "Half"
        }

        llEmptySelected.setOnClickListener{ view ->
//        changing image
            llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full_fade)
            llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half_fade)
            llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_2)
//        changing text opacity
            llFullSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            llHalfSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            llEmptySelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            gasState  ="Empty"

        }

        //REGULAR LEAK SOLD CYLINDER
        tvRegular.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(ivRlctoggleActive, "translationX", 0f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            llRegular.isVisible = true
            llLeak.isVisible = false
            llSold.isVisible = false
        }

        tvLeak.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(ivRlctoggleActive, "translationX", 235f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            llRegular.isVisible = false
            llLeak.isVisible = true
            llSold.isVisible = false

        }

        tvSold.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(ivRlctoggleActive, "translationX", 470f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            llRegular.isVisible = false
            llLeak.isVisible = false
            llSold.isVisible = true

        }

        //for regular
        etGas1R.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas2R.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas3R.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas4R.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        //for leak
        etGas1L.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas2L.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas3L.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas4L.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        //for sold
        etGas1S.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas2S.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas3S.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        etGas4S.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") { //do your work here
                    rslTotal()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

//Send/Receive
        llStockSend.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(ivSendFangro, "translationX", 0f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(ivRecieveFangro, "translationX", -250f).apply {
                duration = 200
                start()
            }



            ivRecieveFangro.isVisible = false
            ivSendFangro.isVisible = true
            sendOrReceive = "Send"

            llStockReceive.background.setTintList(this?.let {
                ContextCompat.getColorStateList(
                    it, R.color.sendreceive_fade)
            })

            llStockSend.background.setTintList(this?.let {
                ContextCompat.getColorStateList(
                    it, R.color.sendreceive)
            })
        }

        llStockReceive.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(ivSendFangro, "translationX", 460f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(ivRecieveFangro, "translationX", 210f).apply {
                duration = 200
                start()
            }

            ivRecieveFangro.isVisible = true
            ivSendFangro.isVisible = false
            sendOrReceive =  "Receive"

            llStockReceive.background.setTintList(this?.let {
                ContextCompat.getColorStateList(
                    it, R.color.sendreceive)
            })

            llStockSend.background.setTintList(this?.let {
                ContextCompat.getColorStateList(
                    it, R.color.sendreceive_fade)
            })
        }

        llGo.setOnClickListener {
            val Gas_state = gasState
            val Regular_Prima = etGas1R.text.toString().toInt()
            val Regular_Kamakhya = etGas2R.text.toString().toInt()
            val Regular_Suvidha = etGas3R.text.toString().toInt()
            val Regular_Others = etGas4R.text.toString().toInt()
            val Leak_Prima = etGas1L.text.toString().toInt()
            val Leak_Kamakhya = etGas2L.text.toString().toInt()
            val Leak_Suvidha = etGas3L.text.toString().toInt()
            val Leak_Others = etGas4L.text.toString().toInt()
            val Sold_Prima = etGas1S.text.toString().toInt()
            val Sold_Kamakhya = etGas2S.text.toString().toInt()
            val Sold_Suvidha = etGas3S.text.toString().toInt()
            val Sold_Others = etGas4S.text.toString().toInt()
            val SendOrReceive = sendOrReceive
            val scheduledDate = ETDate.text.toString()
            val scheduledTime = TVTime.text.toString()
            val Remarks = TVTime.text.toString()

            val scheduleResellerStock = ScheduleResellerStock(
                ResellerID = "123456",
                Gas_state = Gas_state,
                Regular_Prima = Regular_Prima,
                Regular_Kamakhya = Regular_Kamakhya,
                Regular_Suvidha = Regular_Suvidha,
                Regular_Others = Regular_Others,
                Leak_Prima = Leak_Prima,
                Leak_Kamakhya = Leak_Kamakhya,
                Leak_Suvidha = Leak_Suvidha,
                Leak_Others = Leak_Others,
                Sold_Prima = Sold_Prima,
                Sold_Kamakhya = Sold_Kamakhya,
                Sold_Suvidha = Sold_Suvidha,
                Sold_Others = Sold_Others,
                SendOrReceive = SendOrReceive,
                scheduledDate = scheduledDate,
                scheduledTime = scheduledTime,
                Remarks = Remarks
            )
            Log.d("OSCAR", "ScheduleResellerStock: $scheduleResellerStock")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val scheduleResellerStockRepository = ScheduleResellerStockRepository()
                    val response = scheduleResellerStockRepository.addScheduleResellerStock(
                        scheduleResellerStock
                    )
                    Log.d("OSCAR", "Res: $response")
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddSchedule,
                                "Reseller Stock Scheduled.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AddSchedule,
                                "Error when Scheduling.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddSchedule,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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

        TVTime.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                TVTime.text = "Time : "+ hourOfDay+" : "+minute

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

    private fun rslTotal(){
        //regular
        val etGas1Rvalue = if (TextUtils.isEmpty(
                etGas1R.getText().toString()
            )
        ) 0 else etGas1R.getText().toString().toInt()

        val etGas2Rvalue = if (TextUtils.isEmpty(
                etGas2R.getText().toString()
            )
        ) 0 else etGas2R.getText().toString().toInt()

        val etGas3Rvalue = if (TextUtils.isEmpty(
                etGas3R.getText().toString()
            )
        ) 0 else etGas3R.getText().toString().toInt()

        val etGas4Rvalue = if (TextUtils.isEmpty(
                etGas4R.getText().toString()
            )
        ) 0 else etGas4R.getText().toString().toInt()

        //leak
        val etGas1Lvalue = if (TextUtils.isEmpty(
                etGas1L.getText().toString()
            )
        ) 0 else etGas1L.getText().toString().toInt()

        val etGas2Lvalue = if (TextUtils.isEmpty(
                etGas2L.getText().toString()
            )
        ) 0 else etGas2L.getText().toString().toInt()

        val etGas3Lvalue = if (TextUtils.isEmpty(
                etGas3L.getText().toString()
            )
        ) 0 else etGas3L.getText().toString().toInt()

        val etGas4Lvalue = if (TextUtils.isEmpty(
                etGas4L.getText().toString()
            )
        ) 0 else etGas4L.getText().toString().toInt()

        //sold
        val etGas1Svalue = if (TextUtils.isEmpty(
                etGas1S.getText().toString()
            )
        ) 0 else etGas1S.getText().toString().toInt()

        val etGas2Svalue = if (TextUtils.isEmpty(
                etGas2S.getText().toString()
            )
        ) 0 else etGas2S.getText().toString().toInt()

        val etGas3Svalue = if (TextUtils.isEmpty(
                etGas3S.getText().toString()
            )
        ) 0 else etGas3S.getText().toString().toInt()

        val etGas4Svalue = if (TextUtils.isEmpty(
                etGas4S.getText().toString()
            )
        ) 0 else etGas4S.getText().toString().toInt()



        val sumR = etGas1Rvalue + etGas2Rvalue + etGas3Rvalue +etGas4Rvalue
        val sumL = etGas1Lvalue + etGas2Lvalue + etGas3Lvalue +etGas4Lvalue
        val sumS = etGas1Svalue + etGas2Svalue + etGas3Svalue +etGas4Svalue
        val sumTotal = sumR + sumL + sumS
        tvGasTotalR.setText(sumR.toString())
        tvGasTotalL.setText(sumL.toString())
        tvGasTotalS.setText(sumS.toString())
        tvGasTotal.setText(sumTotal.toString())
    }

    private fun showPopupReseller() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = ResellerStockViewAdapter(this,resellerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
                val resellerStock = ResellerStockDetails.getResellerStockDetails()
                title.text = ResellerDetails.getReseller().reseller_fullname
                subtitle.text = ResellerDetails.getReseller().pasal_name
                address.text = ResellerDetails.getReseller().address
                ivCall.contentDescription = ResellerDetails.getReseller().phone_number
                tvDashboardRate.text = ResellerStockDetails.getResellerStockDetails().Amount
                val TotalCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+
                        resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()+resellerStock.Regular_Kamakhya!!.toInt()+
                        resellerStock.Regular_Prima!!.toInt()+resellerStock.Regular_Suvidha!!.toInt()+resellerStock.Regular_Others!!.toInt()+
                        resellerStock.Sold_Kamakhya!!.toInt()+resellerStock.Sold_Suvidha!!.toInt()+resellerStock.Sold_Prima!!.toInt()+
                        resellerStock.Sold_Others!!.toInt()
                if(resellerStock.Gas_state=="Half"){
                    val halfCylinder = TotalCylinder
                    tvDashboardHalfcylinder.text=halfCylinder.toString()
                }else{
                    tvDashboardHalfcylinder.text="0"
                }
                val leakCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()
                tvDashboardLeakcylinder.text = leakCylinder.toString()
                tvDashboardTag.text
                tvDashboardBurn.text
                tvDashboardCylinder.text=TotalCylinder.toString()
            }
        })
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create();

        alertDialog.show();
        val lp = WindowManager.LayoutParams()

        lp.copyFrom(alertDialog.window!!.attributes)
        lp.height = 1550
        lp.x = 0
        lp.y = 50
        alertDialog.getWindow()!!.setAttributes(lp);
        alertDialog.getWindow()!!.setBackgroundDrawableResource(R.color.dark_fade);
        alertDialog.setCanceledOnTouchOutside(true);

//        recyclerView.setOnClickListener(View.OnClickListener {
//            alertDialog.dismiss()
//
//        })

        val llNameSelected = alertDialog.findViewById(R.id.llNameSelected) as LinearLayout
        llNameSelected.setOnClickListener(View.OnClickListener { //do something here
            alertDialog.dismiss()
        })
    }

    private fun showPopupCompany() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = CompanyStockViewAdapter(this,companyList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
                title.text = CompanyDetails.getCompany().cylinder_name
                subtitle.text = CompanyDetails.getCompany().company_fullname
                address.text = CompanyDetails.getCompany().address
            }
        })
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create();

        alertDialog.show();
        val lp = WindowManager.LayoutParams()

        lp.copyFrom(alertDialog.window!!.attributes)
        lp.height = 1550
        lp.x = 0
        lp.y = 50
        alertDialog.getWindow()!!.setAttributes(lp);
        alertDialog.getWindow()!!.setBackgroundDrawableResource(R.color.dark_fade);
        alertDialog.setCanceledOnTouchOutside(true);


        val llNameSelected = alertDialog.findViewById(R.id.llNameSelected) as LinearLayout
        llNameSelected.setOnClickListener(View.OnClickListener { //do something here
            alertDialog.dismiss()
        })
    }
}