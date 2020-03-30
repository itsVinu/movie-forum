package com.example.moviesforum.FragmentViews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesforum.MovieFragments.NowPlayingFragment
import com.example.moviesforum.MovieFragments.PopularFragment
import com.example.moviesforum.MovieFragments.TopRatedFragment
import com.example.moviesforum.MovieFragments.UpcomingFragment

@Suppress("DEPRECATION")
class MyMovieFragmentView (fm:FragmentManager) : FragmentPagerAdapter(fm) {
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