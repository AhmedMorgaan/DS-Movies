package com.example.ds_movies.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.SomaActivity
import com.example.ds_movies.databinding.FragmentSignUpTapBinding

class SignUpFragment :BaseFragment<FragmentSignUpTapBinding,SignUpViewModel>(R.layout.fragment_sign_up_tap) {

    override fun getViewBinding(v: View): FragmentSignUpTapBinding {
        return FragmentSignUpTapBinding.bind(v)
    }
    override val viewModel by viewModels<SignUpViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.setLifecycleOwner { this.lifecycle }

        binding.apply {
            btnSignUp.setOnClickListener {
                viewModel.register()
            }
            viewModel.registerSuccess.observe(viewLifecycleOwner, Observer {
                if (it){
                    val intent = Intent(context,SomaActivity::class.java)
                    startActivity(intent)
                }
            })

            viewModel.fNameError.observe(viewLifecycleOwner, Observer {
                if(it==true){ etFirstName.error = "Not valid name" } })

            viewModel.lNameError.observe(viewLifecycleOwner, Observer {
                if(it==true){ etLastName.error = "Not valid name" } })

            viewModel.emailError.observe(viewLifecycleOwner, Observer {
                if(it==true){ etEmailSignUp.error = "Please enter a valid email" } })

            viewModel.passwordError.observe(viewLifecycleOwner, Observer {
                if(it==true){ etPasswordSignUp.error = "Password required minimum 8 characters" } })

            viewModel.confirmPasswordError.observe(viewLifecycleOwner, Observer {
                if(it==true){ etConfirmPasswordSignUp.error = "Confirm password is wrong" } })

        }
    }
}