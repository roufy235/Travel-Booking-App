package com.example.roufy235.travel.Controllers

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.roufy235.travel.Fragments.Explore
import com.example.roufy235.travel.Fragments.Home
import com.example.roufy235.travel.Fragments.Profile
import com.example.roufy235.travel.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private val homeFragment = Home()
    private val profileFragment = Profile()
    private val exploreFragment = Explore()
    lateinit var viewPager : ViewPager

    override fun onPageScrollStateChanged(state : Int) {

    }

    override fun onPageScrolled(position : Int, positionOffset : Float, positionOffsetPixels : Int) {

    }

    override fun onPageSelected(position : Int) {
        navigation.menu.getItem(position).isChecked = true
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        viewPager.currentItem = item.order
         true
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        viewPager.addOnPageChangeListener(this)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position : Int) : Fragment? {
                when (position) {
                    0 -> {
                        return homeFragment
                    }
                    1 -> {
                        return exploreFragment
                    }
                    2 -> {
                        return profileFragment
                    }
                    else -> {
                        return  null
                    }
                }
            }

            override fun getCount() : Int {
                return  3
            }

        }
    }
}
