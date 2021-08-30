package com.example.cylinderwearos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.cylinderwearos.databinding.ActivityCylinderWearOsBinding
import com.example.cylinderwearos.repository.StockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class cylinderWearOS : Activity() {

    private lateinit var txtBestSellingKamakhya: TextView
    private lateinit var txtBestSellingPrima: TextView
    private lateinit var txtBestSellingSuvidha: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cylinder_wear_os)

        txtBestSellingKamakhya = findViewById(R.id.txtBestSellingKamakhya)
        txtBestSellingPrima = findViewById(R.id.txtBestSellingPrima)
        txtBestSellingSuvidha = findViewById(R.id.txtBestSellingSuvidha)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val stockRepository = StockRepository()
                val response2 = stockRepository.bestSelling()

                    withContext(Dispatchers.Main) {
                        txtBestSellingPrima.setText("${response2.Prima_BestSelling}")
                        txtBestSellingSuvidha.setText("${response2.Suvidha_BestSelling}")
                        txtBestSellingKamakhya.setText("${response2.Kamakhya_BestSelling}")

                    }

            }catch (ex: Exception){
                Log.d("sabin", "Error ayo ${ex.toString()}")
            }
        }

    }
}