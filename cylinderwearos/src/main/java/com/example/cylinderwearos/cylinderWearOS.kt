package com.example.cylinderwearos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.cylinderwearos.databinding.ActivityCylinderWearOsBinding
import com.example.cylinderwearos.repository.StockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class cylinderWearOS : Activity() {

    private lateinit var binding: ActivityCylinderWearOsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCylinderWearOsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Sabin","Here")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val stockRepository = StockRepository()
                val response2 = stockRepository.bestSelling()

                Log.d("Sabin","Herehhyu : $response2")

                withContext(Dispatchers.Main) {
                    binding.txtBestSellingKamakhya.setText("${response2.Kamakhya_BestSelling}")
                    binding.txtBestSellingPrima.setText("${response2.Prima_BestSelling}")
                    binding.txtBestSellingSuvidha.setText("${response2.Suvidha_BestSelling}")

                    Log.d("Sabin","Output: ${response2.Kamakhya_BestSelling}")
                }
            }catch (ex: Exception){
                Log.d("Sabin","Error ayo")

            }
        }

    }
}