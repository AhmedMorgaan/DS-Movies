package com.example.ds_movies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.viewModels
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.databinding.FragmentHomeBinding
import com.example.ds_movies.ui.home.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
    lateinit var  actionBarDrawerToggle : ActionBarDrawerToggle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTablayoutView()
        initDrawerButton()
    }

    private fun initDrawerButton() {
        binding.appbar.setNavigationOnClickListener {
            binding.mainDrawer.openDrawer(binding.navigationView)
        }
       actionBarDrawerToggle = ActionBarDrawerToggle(activity,binding.mainDrawer,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    private fun initTablayoutView(){
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.isUserInputEnabled = false
        binding.typeTablayout.addTab(binding.typeTablayout.newTab().setText("Movies"),true)
        binding.typeTablayout.addTab(binding.typeTablayout.newTab().setText("Tv Shows"))
        binding.typeTablayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
                Log.e("currentItem", "onTabSelected: ${binding.viewPager.currentItem}", )
                Log.e("tabpostion", "onTabSelected: ${tab.position}", )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
       // binding.typeTablayout.getTabAt(0)?.select()
    }


    override fun getViewBinding(v: View): FragmentHomeBinding {
        return FragmentHomeBinding.bind(v)
    }
}