package com.example.moviesforum.adapter.FragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesforum.fragment.MovieFragments.NowPlayingFragment
import com.example.moviesforum.fragment.MovieFragments.PopularFragment
import com.example.moviesforum.fragment.MovieFragments.TopRatedFragment
import com.example.moviesforum.fragment.MovieFragments.UpcomingFragment

@Suppress("DEPRECATION")
class MyMovieFragmentAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {
                UpcomingFragment()
            }
            1 -> {
                TopRatedFragment()
            }
            2 -> {
                PopularFragment()
            }
            else -> {
                return NowPlayingFragment()
            }
            // this method set our tabs position
        }
    }

    override fun getCount(): Int {      // this method return 3 tabs
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {            // this method set the titles of our tabs
        return when(position) {
            0 -> "Upcoming"
            1 -> "Top Rated"
            2 -> "Popular"
            else -> {
                return "Now Playing"
            }
        }
    }
}