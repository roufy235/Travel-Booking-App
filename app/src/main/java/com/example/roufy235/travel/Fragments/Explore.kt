package com.example.roufy235.travel.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roufy235.travel.R

class Explore : Fragment() {

    override fun onCreateView(inflater : LayoutInflater?, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return  inflater!!.inflate(R.layout.fragment_explore, container, false)
    }
}