package com.example.roufy235.travel.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.roufy235.travel.Model.ExploreListModel
import com.example.roufy235.travel.R

class ExploreRecyclerAdapter(val context : Context, val exploreList : ArrayList<ExploreListModel>, val itemClicked : (ExploreListModel) -> Unit) : RecyclerView.Adapter<ExploreRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ticket_explore, parent, false)

        return ViewHolder(view, itemClicked)
    }

    override fun getItemCount() : Int {
        return exploreList.count()
    }

    override fun onBindViewHolder(holder : ViewHolder?, position : Int) {
        holder!!.bindView(context, exploreList[position])
    }


    inner class ViewHolder(itemView : View?, val itemClicked : (ExploreListModel) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val location : TextView = itemView!!.findViewById(R.id.tourName)
        val images : ImageView = itemView!!.findViewById(R.id.tourImages)

        fun bindView(context : Context, explore : ExploreListModel) {
            val resId = context.resources.getIdentifier(explore.image, "drawable", context.packageName)

            location.text = explore.location
            images.setImageResource(resId)

            itemView.setOnClickListener { itemClicked(explore) }
        }
    }
}