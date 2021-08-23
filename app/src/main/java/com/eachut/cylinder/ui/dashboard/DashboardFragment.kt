package com.eachut.cylinder.ui.dashboard

import android.R.interpolator.linear
import android.content.Intent
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
import com.eachut.cylinder.databinding.FragmentDashboardBinding
import com.eachut.cylinder.repository.ResellerRepository
import com.eachut.cylinder.repository.StockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardFragment : Fragment() {

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
                        binding.txtProfit.setText("${response4.profitLossAmount}")
                        binding.txtInvestment.setText("${response4.investment}")
                    }
                }

            }catch (ex: Exception) {

            }
        }


// BEST SELLER
        var primaTotalSell = 3544.toFloat() //3544
        var KamakhyaTotalSell = 1500.toFloat()
        var SuvidhaTotalSell = 2000.toFloat()

        var primaPercent = (primaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
        var kamakhyaPercent = (KamakhyaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
        var suvidhaPercent = (SuvidhaTotalSell * 100) /(primaTotalSell + KamakhyaTotalSell + SuvidhaTotalSell)
        Log.d("oscar", "prima gas percentage is $primaPercent")
        Log.d("oscar", "k gas percentage is $kamakhyaPercent")
        Log.d("oscar", "s gas percentage is $suvidhaPercent")

        var primaWeight = primaPercent / 50
        var primaBlankWeight = 2 - primaWeight
        var kamakhyaWeight = kamakhyaPercent / 50
        var kamakhyaBlankWeight = 2 - kamakhyaWeight
        var suvidhaWeight = suvidhaPercent / 50
        var suvidhaBlankWeight = 2 - suvidhaWeight

        Log.d("oscar", "prima gas w is $primaWeight")
        Log.d("oscar", "prima gas wb is $primaBlankWeight")
        Log.d("oscar", "k gas w is $kamakhyaWeight")
        Log.d("oscar", "k gas wb is $kamakhyaBlankWeight")
        Log.d("oscar", "s gas w is $suvidhaWeight")
        Log.d("oscar", "s gas wb is $suvidhaBlankWeight")

//        binding.ivPrimaBS
        binding.ivPrimaBS.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1F
            )
        )

        binding.ivPrimaBSE.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1F
            )
        )


//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}