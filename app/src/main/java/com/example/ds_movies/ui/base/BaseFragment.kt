package com.example.d_note.Base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ds_movies.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_login_tap.view.*

abstract class BaseFragment<T : ViewDataBinding , VM : BaseViewModel>(resId :Int) : Fragment(resId) {
    lateinit var activity : AppCompatActivity

    lateinit var binding :T
   protected abstract fun getViewBinding(v : View):T
    protected abstract val viewModel :VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewBinding(view)

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.root.progress_bar.visibility = View.VISIBLE
            }else{
                binding.root.progress_bar.visibility = View.GONE
            }
        })

        viewModel.showMessage.observe(viewLifecycleOwner, Observer {
            showMessage("Error",it,"ok", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss() },null,null,false)
        })

    }



    fun showMessage(title:String?,
                    message:String?,
                    posActionName:String?,
                    posAction: DialogInterface.OnClickListener?,
                    negActionName:String?,
                    negAction: DialogInterface.OnClickListener?,
                    isCancelable:Boolean){
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton(posActionName,posAction)
        dialogBuilder.setNegativeButton(negActionName,negAction)
        dialogBuilder.setCancelable(isCancelable)
        dialogBuilder.show()

    }


    fun showMessage(title:Int?,
                    message:Int?,
                    posActionName:Int?,
                    posAction: DialogInterface.OnClickListener?,
                    negAtionNmae:Int?,
                    negAction: DialogInterface.OnClickListener?,
                    isCancelable:Boolean){
        val dialogBuilder = AlertDialog.Builder(activity)
        if (title!=null)
            dialogBuilder.setTitle(title)
        if (message!=null)
            dialogBuilder.setMessage(message)
        if (posActionName !=null)
            dialogBuilder.setPositiveButton(posActionName,posAction)
        if (negAtionNmae !=null)
            dialogBuilder.setNegativeButton(negAtionNmae,negAction)
        dialogBuilder.setCancelable(isCancelable)
        dialogBuilder.show()

    }

}