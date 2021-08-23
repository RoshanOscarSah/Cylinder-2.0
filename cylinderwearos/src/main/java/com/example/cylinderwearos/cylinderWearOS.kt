package com.example.cylinderwearos

import android.app.Activity
import android.os.Bundle
import com.example.cylinderwearos.databinding.ActivityCylinderWearOsBinding

class cylinderWearOS : Activity() {

    private lateinit var binding: ActivityCylinderWearOsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCylinderWearOsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}