package com.eachut.cylinder

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.telephony.gsm.SmsManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.eachut.cylinder.entity.*
import com.eachut.cylinder.repository.CompanyStockRepository
import com.eachut.cylinder.repository.NotificationRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.random.Random as Random1

class ReceiptActivity : AppCompatActivity() {
    private lateinit var llpdf : LinearLayout
    private lateinit var rlbottom : RelativeLayout
    private lateinit var txttitlename : TextView
    private lateinit var txtaddress : TextView
    private lateinit var txtCname : TextView
    private lateinit var txtSname : TextView
    private lateinit var txtFull : TextView
    private lateinit var txtDate : TextView
    private lateinit var txtSerialno : TextView
    private lateinit var txtSend : TextView
    private lateinit var txtprimanumber : TextView
    private lateinit var txtkamakhyanumber : TextView
    private lateinit var txtsubhidanumber : TextView
    private lateinit var txtothersnumber : TextView
    private lateinit var txtRate : TextView
    private lateinit var txtLeak : TextView
    private lateinit var txtPurchase : TextView
    private lateinit var txtSold : TextView
    private lateinit var txtCylinder : TextView
    private lateinit var txtCash : TextView
    private lateinit var btnSubmit:TextView
    private lateinit var btnDownload:Button
    private lateinit var btnSendmessage:Button
    private lateinit var llGo : LinearLayout

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.SEND_SMS
    )
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        llpdf = findViewById(R.id.llpdf)
        rlbottom = findViewById(R.id.rlbottom)
        txttitlename = findViewById(R.id.txttitlename)
        txtaddress = findViewById(R.id.txtaddress)
        txtCname = findViewById(R.id.txtCname)
        txtSname = findViewById(R.id.txtSname)
        txtFull = findViewById(R.id.txtFull)
        txtDate = findViewById(R.id.txtDate)
        txtSerialno = findViewById(R.id.txtSerialno)
        txtSend = findViewById(R.id.txtSend)
        txtprimanumber = findViewById(R.id.txtprimanumber)
        txtkamakhyanumber = findViewById(R.id.txtkamakhyanumber)
        txtsubhidanumber = findViewById(R.id.txtsubhidanumber)
        txtothersnumber = findViewById(R.id.txtothersnumber)
        txtRate = findViewById(R.id.txtRate)
        txtLeak = findViewById(R.id.txtLeak)
        txtPurchase = findViewById(R.id.txtPurchase)
        txtSold = findViewById(R.id.txtSold)
        txtCylinder = findViewById(R.id.txtCylinder)
        txtCash = findViewById(R.id.txtCash)
        btnDownload = findViewById(R.id.btnDownload)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnSendmessage = findViewById(R.id.btnSendmessage)
        llGo = findViewById(R.id.llGo)

        val imgbtnBack = findViewById<View>(R.id.imgbtnBack) as ImageView
        imgbtnBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })

        btnDownload.setOnClickListener(View.OnClickListener {
            Log.d("size", " " + llpdf.getWidth() + "  " + llpdf.getWidth())
            bitmap = loadBitmapFromView(llpdf, llpdf.getWidth(), llpdf.getHeight())
            createPdf()
        })


        if (!hasPermissions()) {
            requestPermission()
        }


        btnSendmessage.setOnClickListener(View.OnClickListener {
            val name = txtCname.text.toString()
            val gasStatus = txtFull.text.toString()
            val sendOrReceive = txtSend.text.toString()
            val p : Int? = txtprimanumber.text.toString().toIntOrNull()
            val k : Int? = txtkamakhyanumber.text.toString().toIntOrNull()
            val s : Int? = txtsubhidanumber.text.toString().toIntOrNull()
            val o : Int? = txtothersnumber.text.toString().toIntOrNull()
            val purchaseTotal = txtPurchase.text.toString()
            val dueTotal = txtCash.text.toString()
            val dueCylinder = txtCylinder.text.toString()
            val txtSerialno = txtSerialno.text.toString()
            val totalCylinder = p!! + k!! + s!! + o!!

            val message = "Dear $name,\n$totalCylinder $gasStatus cylinder are $sendOrReceive. " +
                    "\nTotal Purchase : Rs.$purchaseTotal.\nDue : $dueCylinder cylinder and  Rs. $dueTotal." +
                    "\nBill no: $txtSerialno \n-Rakesh Kirana Pasal"

//            val message = "Dear $name, $totalCylinder $gasStatus cylinder are $sendOrReceive. " +
//                    "Total Purchase : Rs. $purchaseTotal. Due : $dueCylinder cylinder and  Rs. $dueTotal." +
//                    "Rakesh Kirana Pasal. Bill no: $txtSerialno"
            val phoneNumber = txtCname.getContentDescription().toString()
//
//            SmsManager.getDefault().sendTextMessage(
//                number,
//                null,
//                messageToSend,
//                null,
//                null
//            )

            Log.d("OSCAR","phoneNo: $phoneNumber, message: $message")
            sendSMS(phoneNumber, message)
//            sendSMS("9801149729", "Some text here")
        })


        val status = intent.getStringExtra("status")
                            //if Company
        if(status=="company"){

            val company = intent.getParcelableExtra<CompanyStock>("companyStock")!!
            val companyInfo = intent.getParcelableExtra<Company>("company")!!
            txtFull.setText("${company.Gas_state}")
            txtprimanumber.setText("${company.Regular_Prima}")
            txtkamakhyanumber.setText("${company.Regular_Kamakhya}")
            txtsubhidanumber.setText("${company.Regular_Kamakhya}")
            txtothersnumber.setText("${company.Regular_Prima}")
            txtCname.setText("${companyInfo.cylinder_name}")
            txtSname.setText("${companyInfo.company_fullname }")
            txtPurchase.setText("${company.Amount}")
            txtSerialno.setText("${company.CompanyReceiptNo}")
            txtLeak.text= (company.Leak_Prima!!.toInt() + company.Leak_Kamakhya!!.toInt() + company.Leak_Suvidha!!.toInt() + company.Leak_Others!!.toInt()).toString()
            txtSold.text = (company.Sold_Prima!!.toInt()+ company.Sold_Kamakhya!!.toInt() + company.Sold_Suvidha!!.toInt() + company.Sold_Others!!.toInt()).toString()
            Toast.makeText(this, "$company", Toast.LENGTH_SHORT).show()

            //for adding notification
            val notification = NotificationHistory(
                Title = "Stock",
                L1 = companyInfo.cylinder_name,
                L2 = companyInfo.company_fullname,
                L3 = " ",
                R1 = company.SendOrReceive + "."+ company.Gas_state + "." + "#" + company.CompanyReceiptNo + "\n" + "P:" + company.Regular_Prima + ",K:" + company.Regular_Kamakhya + ",S:" + company.Regular_Suvidha + ",O:" + company.Regular_Others,
                R2 = company.Amount,
                Action = "Accepted"
            )
            btnSubmit.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val companyRepository  = CompanyStockRepository()
                        val notificationRepository = NotificationRepository()
                        val companyResponse = companyRepository.addCompanyStock(company)
                        val response2 = notificationRepository.addNotification(notification)
//                        withContext(Main){
//                            Toast.makeText(this@ReceiptActivity, "${companyResponse}", Toast.LENGTH_SHORT).show()
//                        }
                        if (companyResponse.success!! && response2.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${companyResponse.message}", Toast.LENGTH_SHORT).show()
                                btnDownload.isVisible = true
                                btnSendmessage.isVisible = true
                                imgbtnBack.isVisible = false
                                llGo.isVisible = false
                            }

                        }
                        else{
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${companyResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }catch(e:Exception){
                        withContext(Main){
                            Toast.makeText(this@ReceiptActivity, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }

                }


            }


        }
        if (status=="reseller"){
            val reseller = intent.getParcelableExtra<ResellerStock>("resellerStock")!!
            val resellerInfo = intent.getParcelableExtra<Reseller>("reseller")!!
            txtFull.setText("${reseller.Gas_state}")
            txtprimanumber.setText("${reseller.Regular_Prima}")
            txtkamakhyanumber.setText("${reseller.Regular_Kamakhya}")
            txtsubhidanumber.setText("${reseller.Regular_Kamakhya}")
            txtothersnumber.setText("${reseller.Regular_Prima}")
            txtCname.setText("${resellerInfo.reseller_fullname}")
            txtSname.setText("${resellerInfo.pasal_name}")
            txtPurchase.setText("${reseller.Amount}")
            txtSerialno.setText("${reseller.ResellerReceiptNo}")
            txtLeak.text= (reseller.Leak_Prima!!.toInt() + reseller.Leak_Kamakhya!!.toInt() + reseller.Leak_Suvidha!!.toInt() + reseller.Leak_Others!!.toInt()).toString()
            txtSold.text = (reseller.Sold_Prima!!.toInt()+ reseller.Sold_Kamakhya!!.toInt() + reseller.Sold_Suvidha!!.toInt() + reseller.Sold_Others!!.toInt()).toString()
            Toast.makeText(this, "$reseller", Toast.LENGTH_SHORT).show()

            //for adding notification
            val notification = NotificationHistory(
                Title = "Stock",
                L1 = resellerInfo.reseller_fullname,
                L2 = resellerInfo.pasal_name,
                L3 = resellerInfo.address,
                R1 = reseller.SendOrReceive + "."+ reseller.Gas_state + "." + "#" + reseller.ResellerReceiptNo + "\n" + "P:" + reseller.Regular_Prima + ",K:" + reseller.Regular_Kamakhya + ",S:" + reseller.Regular_Suvidha + ",O:" + reseller.Regular_Others,
                R2 = reseller.Amount,
                Action = "Accepted"
            )
            btnSubmit.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val resellerRepository  = ResellerStockRepository()
                        val resellerResponse = resellerRepository.addResellerStock(reseller)
                        val notificationRepository = NotificationRepository()
                        val response2 = notificationRepository.addNotification(notification)
                        if (resellerResponse.success!! && response2.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${resellerResponse.message}", Toast.LENGTH_SHORT).show()
                                btnDownload.isVisible = true
                                btnSendmessage.isVisible = true
                                imgbtnBack.isVisible = false
                                llGo.isVisible = false
                            }

                        }else{
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${resellerResponse.message}", Toast.LENGTH_SHORT).show()
                            }
                        }

                }catch (e:Exception){
                    withContext(Main){
                        Toast.makeText(this@ReceiptActivity, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }

                    }
                }

            }



        }

    }
    private fun createPdf() {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val displaymetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val hight = displaymetrics.heightPixels.toFloat()
        val width = displaymetrics.widthPixels.toFloat()
        val convertHighet = hight.toInt()
        val convertWidth = width.toInt()

        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        canvas.drawPaint(paint)
        bitmap = Bitmap.createScaledBitmap(bitmap!!, convertWidth, convertHighet, true)
        paint.color = Color.BLUE
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        document.finishPage(page)

        // write the document content
        val targetPdf =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val filePath: File
        filePath = File(targetPdf, "CylinderReceipt.pdf")
        try {
            with(document) { writeTo(FileOutputStream(filePath)) }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
        }

        // close the document
        document.close()
        Toast.makeText(this, "Receipt PDF is created!!!", Toast.LENGTH_LONG).show()
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        Log.v("phoneNumber", phoneNumber)
        Log.v("MEssage", message)
        //   PendingIntent pi = PendingIntent.getActivity(this, 0,
        //       new Intent(this, Main.class), 0);
        val SENT = "SMS_SENT"
        val DELIVERY = "SMS_DELIVERED"

        val intent = Intent(SENT)
        val sentIntent = PendingIntent.getBroadcast(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)
        val intent2  = Intent(DELIVERY)
        val deliveryIntent = PendingIntent.getBroadcast(this, 0, intent2,
            PendingIntent.FLAG_ONE_SHOT)

        val sms = SmsManager.getDefault()
        sms.sendTextMessage(phoneNumber, null, message,  sentIntent, deliveryIntent)
        Toast.makeText(this, "message send ($message)", Toast.LENGTH_LONG).show()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            permissions, 12
        )
    }

    private fun hasPermissions(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false
            }
        }
        return hasPermission
    }

    companion object {
        fun loadBitmapFromView(v: View?, width: Int, height: Int): Bitmap {
            val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val c = Canvas(b)
            v!!.draw(c)
            return b
        }
    }
}