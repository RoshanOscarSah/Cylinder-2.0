package com.eachut.cylinder

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.view.WindowManager
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.CompanyStockRepository
import com.eachut.cylinder.repository.ResellerStockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.Exception
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument.PageInfo
import android.net.Uri
import android.os.Environment.getExternalStoragePublicDirectory
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible

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

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
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

        btnDownload.setOnClickListener(View.OnClickListener {
            Log.d("size", " " + llpdf.getWidth() + "  " + llpdf.getWidth())
            bitmap = loadBitmapFromView(llpdf, llpdf.getWidth(), llpdf.getHeight())
            createPdf()
        })

        if (!hasPermissions()) {
            requestPermission()
        }
//new comment

        val status = intent.getStringExtra("status")


                            //Company
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
            txtSerialno.setText("${companyInfo._id}")
            txtLeak.text= (company.Leak_Prima!!.toInt() + company.Leak_Kamakhya!!.toInt() + company.Leak_Suvidha!!.toInt() + company.Leak_Others!!.toInt()).toString()
            txtSold.text = (company.Sold_Prima!!.toInt()+ company.Sold_Kamakhya!!.toInt() + company.Sold_Suvidha!!.toInt() + company.Sold_Others!!.toInt()).toString()

            btnSubmit.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val companyRepository  = CompanyStockRepository()
                        val companyResponse = companyRepository.addCompanyStock(company)
                        if (companyResponse.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${companyResponse.message}", Toast.LENGTH_SHORT).show()
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
            txtSerialno.setText("${resellerInfo._id}")
            txtLeak.text= (reseller.Leak_Prima!!.toInt() + reseller.Leak_Kamakhya!!.toInt() + reseller.Leak_Suvidha!!.toInt() + reseller.Leak_Others!!.toInt()).toString()
            txtSold.text = (reseller.Sold_Prima!!.toInt()+ reseller.Sold_Kamakhya!!.toInt() + reseller.Sold_Suvidha!!.toInt() + reseller.Sold_Others!!.toInt()).toString()

            btnSubmit.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val resellerRepository  = ResellerStockRepository()
                        val resellerResponse = resellerRepository.addResellerStock(reseller)
                        if (resellerResponse.success!!){
                            withContext(Main){
                                Toast.makeText(this@ReceiptActivity, "${resellerResponse.message}", Toast.LENGTH_SHORT).show()
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

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            permissions, 1
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