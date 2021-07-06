package com.eachut.cylinder.ui.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.eachut.cylinder.R


class Addmember : Fragment() {

    private lateinit var admin: LinearLayout
    private lateinit var employe: LinearLayout
    private lateinit var commission: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addmember, container, false)

        admin = view.findViewById(R.id.admin)
        employe = view.findViewById(R.id.employe)
        commission = view.findViewById(R.id.commission)

        employe.setOnClickListener{
            commission.visibility = View.VISIBLE
        }


        return view


    }

}