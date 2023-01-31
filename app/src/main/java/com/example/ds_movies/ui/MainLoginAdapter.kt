package com.example.ds_movies.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ds_movies.ui.login.LoginFragment
import com.example.ds_movies.ui.register.SignUpFragment

class MainLoginAdapter(fm:FragmentManager, context :Context) : FragmentPagerAdapter(fm)  {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                LoginFragment()
            }
            1-> {
                SignUpFragment()
            }
            else -> LoginFragment()
        }
    }
}