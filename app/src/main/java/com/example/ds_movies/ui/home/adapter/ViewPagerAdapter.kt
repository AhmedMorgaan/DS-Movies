package com.example.ds_movies.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ds_movies.ui.moviesTab.MoviesTabFragment
import com.example.ds_movies.ui.tvShowsTab.TvShowsTabFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MoviesTabFragment()
            1 -> return TvShowsTabFragment()
        }
        return TvShowsTabFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }

}