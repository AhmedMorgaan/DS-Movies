package com.example.ds_movies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.data.models.ResultsItem
import com.example.ds_movies.databinding.FragmentMainCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainCategoryFragment : BaseFragment<FragmentMainCategoryBinding,MainCategoryViewModel>(R.layout.fragment_main_category) {


    private  val TAG = "MainCategoryFragment"
    override val viewModel :MainCategoryViewModel by viewModels()
    lateinit var adapter : ParentCategoryListAdapter
    private var  movieList :MutableList<ResultsItem?>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"onViewCreated")


        viewModel.moviesList.observe(viewLifecycleOwner, Observer {
            if (it !=null) {
                movieList = it
               // Log.e(TAG,movieList.toString())
            }else{
                Toast.makeText(requireContext(),"error",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getMoviesCategory()
        viewModel.moviesCategory.observe(viewLifecycleOwner, Observer {

                adapter = ParentCategoryListAdapter(it,viewModel)
                binding.categoryParentRecyclerview.setHasFixedSize(true)
                binding.categoryParentRecyclerview.adapter = adapter
                Log.e(TAG, it.toString())
        })
    }

    override fun getViewBinding(v: View): FragmentMainCategoryBinding {
        return FragmentMainCategoryBinding.bind(v)
    }
}