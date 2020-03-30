package com.example.moviesforum.FragmentViews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesforum.DiscoverFragments.MovieDiscoverFragment
import com.example.moviesforum.DiscoverFragments.TvDiscoverFragment
import com.example.moviesforum.MovieFragments.NowPlayingFragment
import com.example.moviesforum.MovieFragments.PopularFragment
import com.example.moviesforum.MovieFragments.TopRatedFragment
import com.example.moviesforum.MovieFragments.UpcomingFragment

@Suppress("DEPRECATION")
class MyDiscoverFragmentView (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {
                MovieDiscoverFragment()
            }

            else -> {
                return TvDiscoverFragment()
            }
            // this method set our tabs position
        }
    }

    override fun getCount(): Int {      // this method return 3 tabs

        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {            // this method set the titles of our tabs
        return when(position) {
            0 -> "Movie"

            else -> {
                return "Tv Series"
            }
        }
    }
}