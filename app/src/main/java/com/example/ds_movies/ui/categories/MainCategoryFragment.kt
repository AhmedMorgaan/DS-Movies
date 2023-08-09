package com.example.ds_movies.ui.categories

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.data.models.Genre
import com.example.ds_movies.databinding.FragmentMainCategoryBinding
import com.example.ds_movies.ui.categories.adapter.ParentCategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainCategoryFragment : BaseFragment<FragmentMainCategoryBinding, MainCategoryViewModel>(R.layout.fragment_main_category) {


    private  val TAG = "MainCategoryFragment"
    override val viewModel : MainCategoryViewModel by viewModels()
    lateinit var parentAdapter : ParentCategoryListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"onViewCreated")

        binding.progressBar.indeterminateDrawable.setColorFilter(Color.RED,android.graphics.PorterDuff.Mode.SRC_IN)
        getMoviesCategory()
    }

    private fun getMoviesCategory(){
        viewModel.getMoviesCategory()
        viewModel.moviesCategory.observe(viewLifecycleOwner) {

            it?.add(0, Genre(1,"Popular"))

            parentAdapter = ParentCategoryListAdapter(it, viewModel)
            binding.categoryParentRecyclerview.adapter = parentAdapter
            binding.categoryParentRecyclerview.setHasFixedSize(true)
            Log.e(TAG, it.toString())
        }
//        parentAdapter.onItemClickListener = object :ParentCategoryListAdapter.OnItemClickListener{
//            override fun onItemClick(pos: Int, item: CategoryResponse.Genre) {
//                val itemId = item.id
////                findNavController().navigate(R.id.action_mainCategoryFragment_to_movieDetailsFragment,itemId)
//            }
//        }
    }


    override fun getViewBinding(v: View): FragmentMainCategoryBinding {
        return FragmentMainCategoryBinding.bind(v)
    }
}