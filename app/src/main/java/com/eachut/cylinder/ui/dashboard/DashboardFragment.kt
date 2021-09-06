package com.eachut.cylinder.ui.dashboard

import android.R.interpolator.linear
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eachut.cylinder.databinding.FragmentDashboardBinding
import com.eachut.cylinder.repository.ResellerRepository
import com.eachut.cylinder.repository.StockRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject


class DashboardFragment : Fragment() {

    private val FCM_API="https://fcm.googleapis.com/fcm/send"
    private val serverKey= "key="+ "AAAA8G-jvlU:APA91bGbqbLKaMHAjXQ_hEzvu1G8Im7x9ydVQYG8Nj200wqmPGx5heZ87KVIPsEzmGfj_On4ZeVP67Ylq0oeSrvRZ1sa341tNJ0QGvizIzPuGIIKXbntXGJERiWNbgKKx1cJhCBoEAeM"
    private val contentType = "application/json"
    private val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(this.context)
    }
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/Enter_topic")

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        PHONE CALL NEXT ORDER
        binding.flPhoneNextOrder.setOnClickListener{ view ->
            val number = binding.flPhoneNextOrder.getContentDescription()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val stockRepository = StockRepository()
                val response = stockRepository.gascylindersold()
                val response2 = stockRepository.bestSelling()
                val response3 = stockRepository.nextOrder()
                val response4 = stockRepository.profitLoss()

                val resellerRepository = ResellerRepository()
                val response5 = resellerRepository.totalreseller()

                withContext(Dispatchers.Main) {
                    binding.txtTotalresellerdashboard.setText("${response5.totalReseller}")
                    binding.txtLatestReseller.setText("${response5.latest?.reseller_fullname}")
                }


                if(response.success == true) {
                    withContext(Dispatchers.Main) {
                        binding.txtGassold.setText("${response.Gas_Sold}")
                        binding.txtCylinderSold.setText("${response.Cylinder_Sold}")
                        binding.txtGassoldAmount.setText("${response.gasAmount}")
                        binding.txtCylinderSoldAmount.setText("${response.cylinderAmount}")
                        binding.txtBestSellingKamakhya.setText("${response2.Kamakhya_BestSelling}")
                        binding.txtBestSellingPrima.setText("${response2.Prima_BestSelling}")
                        binding.txtBestSellingSuvidha.setText("${response2.Suvidha_BestSelling}")
                        binding.txtCompanyname.setText("${response3.nextOrder}")
                        binding.txtNoofCylinder.setText("${response3.left}")





                        val number:Double = response4.profitLossAmount.toString().toDouble()
                        val number3digits:Double = String.format("%.3f", number).toDouble()
                        val profitloss:Double = String.format("%.2f", number3digits).toDouble()

                        val number2:Double = response4.investment.toString().toDouble()
                        val number3digits2:Double = String.format("%.3f", number2).toDouble()
                        val investment:Double = String.format("%.2f", number3digits2).toDouble()

                        binding.txtProfit.setText("${profitloss}")
                        binding.txtInvestment.setText("${investment}")


                        // BEST SELLER
                        var primaTotalSell = binding.txtBestSellingPrima.text.toString().toFloat()
                        var KamakhyaTotalSell = binding.txtBestSellingKamakhya.text.toString().toFloat()
                        var SuvidhaTotalSell = binding.txtBestSellingSuvidha.text.toString().toFloat()
                        Log.d("oscar", "prima gas percentage is $primaTotalSell")
                        Log.d("oscar", "k gas percentage is $KamakhyaTotalSell")
                        Log.d("oscar", "s gas percentage is $SuvidhaTotalSell")

                        var primaPercent = (primaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
                        var kamakhyaPercent = (KamakhyaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
                        var suvidhaPercent = (SuvidhaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
                        Log.d("oscar", "prima gas percentage is $primaPercent")
                        Log.d("oscar", "k gas percentage is $kamakhyaPercent")
                        Log.d("oscar", "s gas percentage is $suvidhaPercent")

//                        var primaWeight = primaPercent / 50
//                        var primaBlankWeight = 2 - primaWeight
//                        var kamakhyaWeight = kamakhyaPercent / 50
//                        var kamakhyaBlankWeight = 2 - kamakhyaWeight
//                        var suvidhaWeight = suvidhaPercent / 50
//                        var suvidhaBlankWeight = 2 - suvidhaWeight

                        binding.primabar.setProgress(primaPercent.toInt()) //100.0
                        binding.primabar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
                        binding.primabar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT))

                        binding.kamakhyabar.setProgress(kamakhyaPercent.toInt())
                        binding.kamakhyabar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));

                        binding.suvidhabar.setProgress(suvidhaPercent.toInt())
                        binding.suvidhabar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));



//                        binding.ivPrimaBSE.setLayoutParams(
//                            LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.FILL_PARENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT,
//                                1F
//                            )
//                        )


                    }
                }

                val nextOrderInt = response3.left.toString().toInt()

                if (nextOrderInt < 5){
                    val topic = "/topics/Enter_topic"
                    val notification = JSONObject()
                    val notificationBody = JSONObject()
                    try{
                        notificationBody.put("title", "Cylinder 2.0")
                        notificationBody.put("message","$nextOrderInt is left.")
                        notification.put("to",topic)
                        notification.put("data",notificationBody)
                        Log.e("TAG","try")
                    }
                    catch (e:JSONException)
                    {
                        Log.e("TAG", "OnCreate: "+e.message)
                    }
                    sendNotification(notification)
                }


            }catch (ex: Exception) {
                Log.e("Anish","$ex")
            }


        }






//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
    private fun sendNotification(notification: JSONObject) {
        Log.e("TAG", "sendNotification")
        val jsonObjectRequest = object : JsonObjectRequest(FCM_API, notification,
            Response.Listener<JSONObject> { response ->
                Log.i("TAG", "onResponse: $response")
//                tvName.setText("")
            },
            Response.ErrorListener {
                try{
                    Toast.makeText(requireContext(), "Request error", Toast.LENGTH_LONG).show()
                    Log.i("TAG", "onErrorResponse: Didn't work")

                }catch(e: Exception){
                    e.printStackTrace()
                }

            }) {
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = serverKey
                params["Content-Type"] = contentType
                return params
            }
        }
        requestQueue.add(jsonObjectRequest)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}