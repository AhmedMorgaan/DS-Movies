package com.example.ds_movies.Base

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open abstract class BaseActivity<T : ViewDataBinding , VM : ViewModel> : AppCompatActivity() {

   // lateinit var activity :AppCompatActivity
    lateinit var dataBinding : T
    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // activity = this
        dataBinding = getViewBinding()
        setContentView(dataBinding.root)
       // dataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel  = generateViewModel()
    }

    abstract fun getLayoutId():Int
    abstract fun getViewBinding(): T
    abstract fun generateViewModel(): VM

    @SuppressLint("SuspiciousIndentation")
    fun showMessage(title:String?,
                    message:String?,
                    posActionName:String?,
                    posAction:DialogInterface.OnClickListener?,
                    negActionName:String?,
                    negAction:DialogInterface.OnClickListener?,
                    isCancelable:Boolean){
        val dialogBuilder = AlertDialog.Builder(this)
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
                    posAction:DialogInterface.OnClickListener?,
                    negActionName:Int?,
                    negAction:DialogInterface.OnClickListener?,
                    isCancelable:Boolean){
        val dialogBuilder = AlertDialog.Builder(this)
        if (title!=null)
        dialogBuilder.setTitle(title)
        if (message!=null)
        dialogBuilder.setMessage(message)
        if (posActionName !=null)
        dialogBuilder.setPositiveButton(posActionName,posAction)
        if (negActionName !=null)
        dialogBuilder.setNegativeButton(negActionName,negAction)
        dialogBuilder.setCancelable(isCancelable)
        dialogBuilder.show()

    }
}