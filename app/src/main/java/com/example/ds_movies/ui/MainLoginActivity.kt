package com.example.ds_movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.ds_movies.R
import com.example.ds_movies.databinding.ActivityMainLoginBinding
import com.example.ds_movies.ui.login.LoginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainLoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    lateinit var dataBinding: ActivityMainLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val fb = findViewById<FloatingActionButton>(R.id.fb_button)
        val viewpager = findViewById<ViewPager>(R.id.login_viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.login_tapLayout)


        dataBinding.loginTapLayout.addTab(dataBinding.loginTapLayout.newTab().setText("Login"))
        dataBinding.loginTapLayout.addTab(dataBinding.loginTapLayout.newTab().setText("SignUp"))
        dataBinding.loginTapLayout.tabGravity=TabLayout.GRAVITY_FILL

        val adapter = MainLoginAdapter(supportFragmentManager, applicationContext)

        viewpager.adapter = adapter
        viewpager.addOnPageChangeListener(object :TabLayout.TabLayoutOnPageChangeListener(tabLayout){})

        fb.translationX = 300F
        tabLayout.translationX = 300F

        fb.alpha = 0F
        tabLayout.alpha = 0F

        fb.animate().translationX(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()
        tabLayout.animate().translationX(0F).alpha(1F).setDuration(1000).setStartDelay(600).start()
    }
}