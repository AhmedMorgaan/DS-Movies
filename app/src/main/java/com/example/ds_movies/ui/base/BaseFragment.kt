package com.example.d_note.Base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ds_movies.ui.base.BaseActivity
import com.example.ds_movies.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_login_tap.view.progress_bar

abstract class BaseFragment<T : ViewDataBinding , VM : BaseViewModel>(resId :Int) : Fragment(resId) {
     private var baseActivity : BaseActivity<*,*>? = null

    lateinit var binding :T
   protected abstract fun getViewBinding(v : View):T
   protected abstract val viewModel :VM

    override fun onAttach(context: Context) {
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
        }
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewBinding(view)
        showHideProgressBar()
        showHideMassage()
    }

    private fun showHideProgressBar(){
        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.root.progress_bar.visibility = View.VISIBLE
            }else{
                binding.root.progress_bar.visibility = View.GONE
            }
        })
    }

    private fun showHideMassage(){
        viewModel.showMessage.observe(viewLifecycleOwner, Observer {
            if (baseActivity != null){
                baseActivity!!.showMessage("Error",it,"ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss() },null,null,false)
            }

        })
    }








}