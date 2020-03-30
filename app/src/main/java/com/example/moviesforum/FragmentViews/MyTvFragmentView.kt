package com.example.moviesforum.FragmentViews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesforum.MovieFragments.NowPlayingFragment
import com.example.moviesforum.MovieFragments.PopularFragment
import com.example.moviesforum.MovieFragments.TopRatedFragment
import com.example.moviesforum.MovieFragments.UpcomingFragment
import com.example.moviesforum.TvFragments.AiringTodayFragment
import com.example.moviesforum.TvFragments.OnTheAirFragment
import com.example.moviesforum.TvFragments.PopularTvFragment
import com.example.moviesforum.TvFragments.TopRatedTvFragment

@Suppress("DEPRECATION")
class MyTvFragmentView (fm: FragmentManager) : FragmentPagerAdapter(fm) {
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