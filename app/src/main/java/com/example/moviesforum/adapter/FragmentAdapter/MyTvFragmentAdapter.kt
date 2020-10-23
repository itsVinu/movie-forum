package com.example.moviesforum.adapter.FragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesforum.fragment.TvFragments.AiringTodayFragment
import com.example.moviesforum.fragment.TvFragments.OnTheAirFragment
import com.example.moviesforum.fragment.TvFragments.PopularTvFragment
import com.example.moviesforum.fragment.TvFragments.TopRatedTvFragment

@Suppress("DEPRECATION")
class MyTvFragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {
                AiringTodayFragment()
            }
            1 -> {
                OnTheAirFragment()
            }
            2 -> {
                PopularTvFragment()
            }
            else -> {
                return TopRatedTvFragment()
            }
            // this method set our tabs position
        }
    }

    override fun getCount(): Int {      // this method return 3 tabs

        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {            // this method set the titles of our tabs
        return when(position) {
            0 -> "Airing"
            1 -> "On The Air"
            2 -> "Popular"
            else -> {
                return "Top Rated"
            }
        }
    }
}