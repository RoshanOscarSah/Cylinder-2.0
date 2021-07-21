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
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.Adapter.CompanyStockViewAdapter
import com.eachut.cylinder.Adapter.ResellerProfileAdapter
import com.eachut.cylinder.Adapter.ResellerStockViewAdapter
import com.eachut.cylinder.LoadingActivity
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentHomeBinding
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.repository.CompanyRepository
import com.eachut.cylinder.repository.ResellerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class   HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var isReseller:Boolean?=null
    private var isCompany:Boolean?=null
    private var resellerList= mutableListOf<Reseller>()
    private var companyList= mutableListOf<Company>()


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
            val customerOrCompany = binding.tvCustomerOrCompany.getContentDescription()
            if(customerOrCompany=="getReseller"){
//                isReseller=true
//                isCompany=false
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        val resellerRepository = ResellerRepository()
                        val response =  resellerRepository.allresellerList()
                        if(response.success!!){
                            resellerList= response.data!!
                            showPopupReseller(resellerList)
                        }
                        else{

                        }
                    }catch(e:Exception){

                    }
                }

            }
            if (customerOrCompany=="getCompany"){
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        val companyRepository = CompanyRepository()
                        val response =  companyRepository.allCompanyList()
                        if(response.success!!){
                            companyList=response.data!!
                            showPopupCompany(companyList)
                        }
                        else{

                        }
                    }catch(e:Exception){

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
    fun showPopupReseller(mutableList: MutableList<Reseller>) {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
                val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
                recyclerView.adapter = ResellerStockViewAdapter(context!!, mutableList)
                recyclerView.layoutManager = LinearLayoutManager(context)            }
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
            val ChangeCustomerOrCompany = binding.tvCustomerOrCompany.getContentDescription()
            binding.tvCustomerOrCompany.setText(ChangeCustomerOrCompany)
            binding.llNameSelected.isVisible = true
        })
    }

    //popup company List
    fun showPopupCompany(mutableList: MutableList<Company>) {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.activity_prename, null)

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
                val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)
                recyclerView.adapter = CompanyStockViewAdapter(context!!, mutableList)
                recyclerView.layoutManager = LinearLayoutManager(context)
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
            val ChangeCustomerOrCompany = binding.tvCustomerOrCompany.getContentDescription()
            binding.tvCustomerOrCompany.setText(ChangeCustomerOrCompany)
            binding.llNameSelected.isVisible = true
        })
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