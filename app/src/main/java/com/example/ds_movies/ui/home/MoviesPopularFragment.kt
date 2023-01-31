package com.example.ds_movies.ui.home

import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.databinding.FragmentMoviesPopularBinding
import com.example.ds_movies.service.MyBroadcastReceiver


class MoviesPopularFragment : BaseFragment<FragmentMoviesPopularBinding,MoviesPopularViewModel>(R.layout.fragment_movies_popular) {

    override val viewModel by viewModels<MoviesPopularViewModel>()

    private var adapter = MoviePopularListAdapter(null)
    val receiver = MyBroadcastReceiver()
    val filter = IntentFilter("android.intent.action.HEADSET_PLUG")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel



        requireActivity().registerReceiver(receiver, filter,)
        viewModel.getResult()
        observeLiveData()

        binding.moviesRecyclerView.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(binding.moviesRecyclerView)

//        val intent = Intent(activity,MyService::class.java)
//        startService(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        //        val intent = Intent(activity,MyService::class.java)
//        stopService(intent)
        requireActivity().unregisterReceiver(receiver)
    }
    private fun observeLiveData() {

        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, Observer {
            showMessage(
                "Error", it, "ok",
                { dialogInterface, i ->
                    dialogInterface.dismiss()
                }, null, null, false
            )
        })
    }

    override fun getViewBinding(v: View): FragmentMoviesPopularBinding {
        return FragmentMoviesPopularBinding.bind(v)
    }

}