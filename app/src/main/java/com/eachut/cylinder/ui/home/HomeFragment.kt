package com.eachut.cylinder.ui.home

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.CompanyStockViewAdapter
import com.eachut.cylinder.Adapter.ResellerStockViewAdapter
import com.eachut.cylinder.Object.CompanyDetails
import com.eachut.cylinder.Object.ResellerDetails
import com.eachut.cylinder.Object.ResellerStockDetails
import com.eachut.cylinder.R
import com.eachut.cylinder.ReceiptActivity
import com.eachut.cylinder.databinding.FragmentHomeBinding
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var isReseller:Boolean?=null
    private var isCompany:Boolean?=null
    private var resellerList= mutableListOf<Reseller>()
    private var companyList= mutableListOf<Company>()
    private var resellerStock = mutableListOf<ResellerStock>()
    private var companyStock = mutableListOf<CompanyStock>()
    private var gasState =  "Full"
    private var sendOrReceive = "Send"
    private var customerOrCompany = String()
    private var ResellerID = String()
    private var CompanyID = String()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Random Receipt Number Generator
        val rnd = Random()
        val number: Int = rnd.nextInt(999999)
        val randomNumber = String.format("%06d", number)

//reseller/company
        binding.tvCompany.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            binding.ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivToggleActive, "translationX", 460f).apply {
//                duration = 200
//                start()
//            }
            binding.tvCustomerOrCompany.setText("Select Company")
            binding.tvCustomerOrCompany.setContentDescription("getCompany")
        }

        binding.tvCustomer.setOnClickListener { view ->
            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            binding.ivToggleActive.setLayoutParams(params);

            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivToggleActive, "translationX", 0f).apply {
//                duration = 200
//                start()
//            }
            binding.tvCustomerOrCompany.setText("Select Reseller")
            binding.tvCustomerOrCompany.setContentDescription("getReseller")
        }

//select customer
        binding.llSelectCustomer.setOnClickListener { view ->
            customerOrCompany = binding.tvCustomerOrCompany.getContentDescription().toString()
            binding.llNameSelected.isVisible = true

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
                            Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            }

        }

//        call customer
        binding.ivCall.setOnClickListener{ view ->
            val number = binding.ivCall.getContentDescription()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }

//Full HALF EMPTY CYLINDER
        binding.llFullSelected.setOnClickListener{ view ->
//        changing image
            binding.llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full)
            binding.llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half_fade)
            binding.llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_fade)
//        changing text opacity
            binding.llFullSelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            binding.llHalfSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            binding.llEmptySelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            gasState = "Full"
        }

        binding.llHalfSelected.setOnClickListener{ view ->
//        changing image
            binding.llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full_fade)
            binding.llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half)
            binding.llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_fade)
//        changing text opacity
            binding.llFullSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            binding.llHalfSelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            binding.llEmptySelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            gasState = "Half"
        }

        binding.llEmptySelected.setOnClickListener{ view ->
//        changing image
            binding.llFullSelectedImg.setImageResource(R.drawable.ic_cylinder_full_fade)
            binding.llHalfSelectedImg.setImageResource(R.drawable.ic_cylinder_half_fade)
            binding.llEmptySelectedImg.setImageResource(R.drawable.ic_cylinder_empty_2)
//        changing text opacity
            binding.llFullSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            binding.llHalfSelectedTxt.setTextColor(Color.parseColor("#4DFFFFFF"));
            binding.llEmptySelectedTxt.setTextColor(Color.parseColor("#FFFFFF"));
            gasState  ="Empty"

        }

//REGULAR LEAK SOLD CYLINDER
        binding.tvRegular.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivRlctoggleActive, "translationX", 0f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            binding.ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            binding.llRegular.isVisible = true
            binding.llLeak.isVisible = false
            binding.llSold.isVisible = false
        }

        binding.tvLeak.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivRlctoggleActive, "translationX", 235f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            binding.ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            binding.llRegular.isVisible = false
            binding.llLeak.isVisible = true
            binding.llSold.isVisible = false

        }

        binding.tvSold.setOnClickListener{ view ->
            //for sliding animation
//            ObjectAnimator.ofFloat(binding.ivRlctoggleActive, "translationX", 470f).apply {
//                duration = 200
//                start()
//            }

            //for setting gravity
            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            binding.ivRlctoggleActive.setLayoutParams(params);

            //hiding other layout
            binding.llRegular.isVisible = false
            binding.llLeak.isVisible = false
            binding.llSold.isVisible = true

        }

        //for regular
        binding.etGas1R.addTextChangedListener(object : TextWatcher {
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

        binding.etGas2R.addTextChangedListener(object : TextWatcher {
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

        binding.etGas3R.addTextChangedListener(object : TextWatcher {
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

        binding.etGas4R.addTextChangedListener(object : TextWatcher {
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
        binding.etGas1L.addTextChangedListener(object : TextWatcher {
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

        binding.etGas2L.addTextChangedListener(object : TextWatcher {
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

        binding.etGas3L.addTextChangedListener(object : TextWatcher {
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

        binding.etGas4L.addTextChangedListener(object : TextWatcher {
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
        binding.etGas1S.addTextChangedListener(object : TextWatcher {
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

        binding.etGas2S.addTextChangedListener(object : TextWatcher {
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

        binding.etGas3S.addTextChangedListener(object : TextWatcher {
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

        binding.etGas4S.addTextChangedListener(object : TextWatcher {
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
        binding.llStockSend.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(binding.ivSendFangro, "translationX", 0f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(binding.ivRecieveFangro, "translationX", -250f).apply {
                duration = 200
                start()
            }



            binding.ivRecieveFangro.isVisible = false
            binding.ivSendFangro.isVisible = true
            sendOrReceive = "Send"

            binding.llStockReceive.background.setTintList(context?.let {
                ContextCompat.getColorStateList(
                        it, R.color.sendreceive_fade)
            })

            binding.llStockSend.background.setTintList(context?.let {
                ContextCompat.getColorStateList(
                        it, R.color.sendreceive)
            })
        }

        binding.llStockReceive.setOnClickListener { view ->

            //for sliding animation
            ObjectAnimator.ofFloat(binding.ivSendFangro, "translationX", 460f).apply {
                duration = 200
                start()
            }

            //for sliding animation
            ObjectAnimator.ofFloat(binding.ivRecieveFangro, "translationX", 210f).apply {
                duration = 200
                start()
            }

            binding.ivRecieveFangro.isVisible = true
            binding.ivSendFangro.isVisible = false
            sendOrReceive =  "Receive"

            binding.llStockReceive.background.setTintList(context?.let {
                ContextCompat.getColorStateList(
                        it, R.color.sendreceive)
            })

            binding.llStockSend.background.setTintList(context?.let {
                ContextCompat.getColorStateList(
                        it, R.color.sendreceive_fade)
            })
        }




// GO
        binding.llGo.setOnClickListener { view ->
            val RandomNumber = randomNumber
            val Gas_state = gasState
            val Regular_Prima = binding.etGas1R.text
            val Regular_Kamakhya = binding.etGas2R.text
            val Regular_Suvidha = binding.etGas3R.text
            val Regular_Others = binding.etGas4R.text
            val Leak_Prima = binding.etGas1L.text
            val Leak_Kamakhya = binding.etGas2L.text
            val Leak_Suvidha = binding.etGas3L.text
            val Leak_Others = binding.etGas4L.text
            val Sold_Prima = binding.etGas1S.text
            val Sold_Kamakhya = binding.etGas2S.text
            val Sold_Suvidha = binding.etGas3S.text
            val Sold_Others = binding.etGas4S.text
            val SendOrReceive = sendOrReceive
            val Amount = binding.ettotalamount.text
            val Remarks = binding.etremarks.text

            if (customerOrCompany=="getReseller"){
                ResellerID = ResellerDetails.getReseller()._id.toString()
                var reseller = ResellerDetails.getReseller()
                val resellerStock = ResellerStock(
                        ResellerReceiptNo = RandomNumber,
                        ResellerID = ResellerID,
                        Gas_state = Gas_state,
                        Regular_Prima = Regular_Prima.toString().toInt(),
                        Regular_Kamakhya = Regular_Kamakhya.toString().toInt(),
                        Regular_Suvidha = Regular_Suvidha.toString().toInt(),
                        Regular_Others = Regular_Others.toString().toInt(),
                        Leak_Prima = Leak_Prima.toString().toInt(),
                        Leak_Kamakhya = Leak_Kamakhya.toString().toInt(),
                        Leak_Suvidha = Leak_Suvidha.toString().toInt(),
                        Leak_Others = Leak_Others.toString().toInt(),
                        Sold_Prima = Sold_Prima.toString().toInt(),
                        Sold_Kamakhya = Sold_Kamakhya.toString().toInt(),
                        Sold_Suvidha = Sold_Suvidha.toString().toInt(),
                        Sold_Others = Sold_Others.toString().toInt(),
                        SendOrReceive = SendOrReceive,
                        Amount = Amount.toString(),
                        Remarks = Remarks.toString())


                val intent = Intent(context, ReceiptActivity::class.java)
                        .putExtra("status","reseller")
                        .putExtra("resellerStock", resellerStock)
                        .putExtra("reseller",reseller)
                startActivity(intent)
            }

            if(customerOrCompany=="getCompany"){
                CompanyID = CompanyDetails.getCompany()._id.toString()
                var company = CompanyDetails.getCompany()
                val companyStock = CompanyStock(
                        CompanyReceiptNo = RandomNumber,
                        CompanyID = CompanyID,
                        Gas_state = Gas_state,
                        Regular_Prima = Regular_Prima.toString().toInt(),
                        Regular_Kamakhya = Regular_Kamakhya.toString().toInt(),
                        Regular_Suvidha = Regular_Suvidha.toString().toInt(),
                        Regular_Others = Regular_Others.toString().toInt(),
                        Leak_Prima = Leak_Prima.toString().toInt(),
                        Leak_Kamakhya = Leak_Kamakhya.toString().toInt(),
                        Leak_Suvidha = Leak_Suvidha.toString().toInt(),
                        Leak_Others = Leak_Others.toString().toInt(),
                        Sold_Prima = Sold_Prima.toString().toInt(),
                        Sold_Kamakhya = Sold_Kamakhya.toString().toInt(),
                        Sold_Suvidha = Sold_Suvidha.toString().toInt(),
                        Sold_Others = Sold_Others.toString().toInt(),
                        SendOrReceive = SendOrReceive,
                        Amount = Amount.toString(),
                        Remarks = Remarks.toString())

                val intent = Intent(context, ReceiptActivity::class.java)
                        .putExtra("status","company")
                        .putExtra("companyStock", companyStock)
                        .putExtra("company",company)
                startActivity(intent)
            }



        }

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //popup Reseller list
    fun showPopupReseller() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)
//        dialogView
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {

//                val resellerStock = ResellerStockDetails.getResellerStockDetails()
//                val flag = ResellerStockDetails.isData()
//                var TotalCylinder = 0
//                var leakCylinder = 0
//                var halfCylinder = 0
//                if (flag){
//                     TotalCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+
//                            resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()+resellerStock.Regular_Kamakhya!!.toInt()+
//                            resellerStock.Regular_Prima!!.toInt()+resellerStock.Regular_Suvidha!!.toInt()+resellerStock.Regular_Others!!.toInt()+
//                            resellerStock.Sold_Kamakhya!!.toInt()+resellerStock.Sold_Suvidha!!.toInt()+resellerStock.Sold_Prima!!.toInt()+
//                            resellerStock.Sold_Others!!.toInt()
//
//                     leakCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()
//
//                    if(resellerStock.Gas_state=="Half"){
//                         halfCylinder = TotalCylinder
//                    }
//
//
//                }
//                binding.tvDashboardHalfcylinder.text=halfCylinder.toString()
//
//                binding.title.text = ResellerDetails.getReseller().reseller_fullname
//                binding.subtitle.text = ResellerDetails.getReseller().pasal_name
//                binding.address.text = ResellerDetails.getReseller().address
//                binding.ivCall.contentDescription = ResellerDetails.getReseller().phone_number
//                binding.tvDashboardRate.text = ResellerStockDetails.getResellerStockDetails().Amount
//
//
//                binding.tvDashboardLeakcylinder.text = leakCylinder.toString()
//                binding.tvDashboardTag.text
//                binding.tvDashboardBurn.text
//                binding.tvDashboardCylinder.text=TotalCylinder.toString()

                val resellerStock = ResellerStockDetails.getResellerStockDetails()
                val flag = ResellerStockDetails.isData()
                var TotalCylinder = 0
                var leakCylinder = 0
                var halfCylinder = 0
                if (flag){
                    TotalCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+
                            resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()+resellerStock.Regular_Kamakhya!!.toInt()+
                            resellerStock.Regular_Prima!!.toInt()+resellerStock.Regular_Suvidha!!.toInt()+resellerStock.Regular_Others!!.toInt()+
                            resellerStock.Sold_Kamakhya!!.toInt()+resellerStock.Sold_Suvidha!!.toInt()+resellerStock.Sold_Prima!!.toInt()+
                            resellerStock.Sold_Others!!.toInt()

                    leakCylinder = resellerStock.Leak_Kamakhya!!.toInt()+resellerStock.Leak_Others!!.toInt()+resellerStock.Leak_Prima!!.toInt()+resellerStock.Leak_Suvidha!!.toInt()

                    if(resellerStock.Gas_state=="Half"){
                        halfCylinder = TotalCylinder
                    }


                }
                binding.tvDashboardHalfcylinder.text=halfCylinder.toString()

                binding.title.text = ResellerDetails.getReseller().reseller_fullname
                binding.subtitle.text = ResellerDetails.getReseller().pasal_name
                binding.address.text = ResellerDetails.getReseller().address
                binding.ivCall.contentDescription = ResellerDetails.getReseller().phone_number
                binding.tvDashboardRate.text = ResellerStockDetails.getResellerStockDetails().Amount


                binding.tvDashboardLeakcylinder.text = leakCylinder.toString()
                binding.tvDashboardTag.text
                binding.tvDashboardBurn.text
                binding.tvDashboardCylinder.text=TotalCylinder.toString()

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

//        val llNameSelected = alertDialog.findViewById(R.id.llNameSelected) as LinearLayout
//        llNameSelected.setOnClickListener(View.OnClickListener { //do something here
//            alertDialog.dismiss()
//        })

        recyclerView.adapter = ResellerStockViewAdapter(requireContext(),resellerList, alertDialog)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
    //popup company List
    fun showPopupCompany() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
                binding.title.text = CompanyDetails.getCompany().cylinder_name
                binding.subtitle.text = CompanyDetails.getCompany().company_fullname
                binding.address.text = CompanyDetails.getCompany().address

            }
        })
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        alertDialog.show();
        val lp = WindowManager.LayoutParams()

        lp.copyFrom(alertDialog.window!!.attributes)
        lp.height = 1550
        lp.x = 0
        lp.y = 50
        alertDialog.getWindow()!!.setAttributes(lp);
        alertDialog.getWindow()!!.setBackgroundDrawableResource(R.color.dark_fade);
        alertDialog.setCanceledOnTouchOutside(true);


//        val llNameSelected = alertDialog.findViewById(R.id.llNameSelected) as LinearLayout
//        llNameSelected.setOnClickListener(View.OnClickListener { //do something here
//            alertDialog.dismiss()
//        })

        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = CompanyStockViewAdapter(requireContext(),companyList, alertDialog)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    //    for RLS total
    fun rslTotal(){
        //regular
        val etGas1Rvalue = if (TextUtils.isEmpty(
                        binding.etGas1R.getText().toString()
                )
        ) 0 else binding.etGas1R.getText().toString().toInt()

        val etGas2Rvalue = if (TextUtils.isEmpty(
                        binding.etGas2R.getText().toString()
                )
        ) 0 else binding.etGas2R.getText().toString().toInt()

        val etGas3Rvalue = if (TextUtils.isEmpty(
                        binding.etGas3R.getText().toString()
                )
        ) 0 else binding.etGas3R.getText().toString().toInt()

        val etGas4Rvalue = if (TextUtils.isEmpty(
                        binding.etGas4R.getText().toString()
                )
        ) 0 else binding.etGas4R.getText().toString().toInt()

        //leak
        val etGas1Lvalue = if (TextUtils.isEmpty(
                        binding.etGas1L.getText().toString()
                )
        ) 0 else binding.etGas1L.getText().toString().toInt()

        val etGas2Lvalue = if (TextUtils.isEmpty(
                        binding.etGas2L.getText().toString()
                )
        ) 0 else binding.etGas2L.getText().toString().toInt()

        val etGas3Lvalue = if (TextUtils.isEmpty(
                        binding.etGas3L.getText().toString()
                )
        ) 0 else binding.etGas3L.getText().toString().toInt()

        val etGas4Lvalue = if (TextUtils.isEmpty(
                        binding.etGas4L.getText().toString()
                )
        ) 0 else binding.etGas4L.getText().toString().toInt()

        //sold
        val etGas1Svalue = if (TextUtils.isEmpty(
                        binding.etGas1S.getText().toString()
                )
        ) 0 else binding.etGas1S.getText().toString().toInt()

        val etGas2Svalue = if (TextUtils.isEmpty(
                        binding.etGas2S.getText().toString()
                )
        ) 0 else binding.etGas2S.getText().toString().toInt()

        val etGas3Svalue = if (TextUtils.isEmpty(
                        binding.etGas3S.getText().toString()
                )
        ) 0 else binding.etGas3S.getText().toString().toInt()

        val etGas4Svalue = if (TextUtils.isEmpty(
                        binding.etGas4S.getText().toString()
                )
        ) 0 else binding.etGas4S.getText().toString().toInt()



        val sumR = etGas1Rvalue + etGas2Rvalue + etGas3Rvalue +etGas4Rvalue
        val sumL = etGas1Lvalue + etGas2Lvalue + etGas3Lvalue +etGas4Lvalue
        val sumS = etGas1Svalue + etGas2Svalue + etGas3Svalue +etGas4Svalue
        val sumTotal = sumR + sumL + sumS
        binding.tvGasTotalR.setText(sumR.toString())
        binding.tvGasTotalL.setText(sumL.toString())
        binding.tvGasTotalS.setText(sumS.toString())
        binding.tvGasTotal.setText(sumTotal.toString())
    }

}