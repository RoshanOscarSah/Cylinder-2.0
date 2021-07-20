package com.eachut.cylinder.ui.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eachut.cylinder.R

class ViewMemberFragment : Fragment() {

    private lateinit var recyclerview : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_member, container, false)

        recyclerview = view.findViewById(R.id.recyclerview)

        return view
    }
}