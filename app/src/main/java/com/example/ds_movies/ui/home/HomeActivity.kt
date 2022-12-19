package com.example.ds_movies.ui.home


import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.ds_movies.Base.BaseActivity
import com.example.ds_movies.MyBroadcastReceiver
import com.example.ds_movies.R
import com.example.ds_movies.adapter.MovieListAdapter
import com.example.ds_movies.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun generateViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private var adapter = MovieListAdapter(null)
    val receiver = MyBroadcastReceiver()
    val filter = IntentFilter("android.intent.action.HEADSET_PLUG")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("homeActivity","on Create")

        dataBinding.vm = viewModel

        registerReceiver(receiver, filter)
        viewModel.getResult()
        observeLiveData()
        movies_recyclerView.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(movies_recyclerView)

//        val intent = Intent(activity,MyService::class.java)
//        startService(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
//        val intent = Intent(activity,MyService::class.java)
//        stopService(intent)

        unregisterReceiver(receiver)
    }

    private fun observeLiveData() {

        viewModel.resultLiveData.observe(this, Observer {
            adapter.changeData(it)
        })
        viewModel.progressBarLiveData.observe(this, Observer {
            if (it) {
                progress_bar.visibility = View.GONE
            }
        })
        viewModel.errorMessageLiveData.observe(this, Observer {
            showMessage(
                "Error", it, "ok",
                { dialogInterface, i ->
                    dialogInterface.dismiss()
                }, null, null, false
            )
        })
    }




}


