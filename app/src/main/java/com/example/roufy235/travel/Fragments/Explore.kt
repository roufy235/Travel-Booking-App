package com.example.roufy235.travel.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roufy235.travel.R
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.roufy235.travel.Adapters.ExploreRecyclerAdapter
import com.example.roufy235.travel.Controllers.FlightsResultActivity
import com.example.roufy235.travel.Model.ExploreListModel
import com.example.roufy235.travel.Services.DataServices
import spencerstudios.com.bungeelib.Bungee


class Explore : Fragment() {

    lateinit var toolbar : Toolbar
    lateinit var adapter : ExploreRecyclerAdapter
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        dummy()
    }

    override fun onResume() {
        super.onResume()
        DataServices.flightsResult.clear()
    }

    fun dummy() {
        DataServices.exploreList.add(ExploreListModel("Seattle, United States (US)", "program"))
        DataServices.exploreList.add(ExploreListModel("London", "program"))
        DataServices.exploreList.add(ExploreListModel("Ottawa, Canada", "greece"))
        DataServices.exploreList.add(ExploreListModel("Montreal, Canada", "canada"))
        DataServices.exploreList.add(ExploreListModel("Paris, France", "canada"))
    }

    override fun onCreateView(inflater : LayoutInflater?, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        toolbar = view.findViewById(R.id.exploreToolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        (activity as AppCompatActivity).supportActionBar!!.title = "Explore the world"

        adapter = ExploreRecyclerAdapter(context, DataServices.exploreList) {exploreListModel ->

            val intent = Intent(context, FlightsResultActivity::class.java)
            intent.putExtra("from", "Lagos")
            intent.putExtra("location", exploreListModel.location)
            intent.putExtra("explore", true)
            startActivity(intent)
            Bungee.fade(context)
        }

        recyclerView = view.findViewById(R.id.exploreRecyclerView)

        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        return  view
    }
}