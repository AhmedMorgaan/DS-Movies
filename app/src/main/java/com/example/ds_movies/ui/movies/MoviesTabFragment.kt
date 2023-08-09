package com.example.ds_movies.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.d_note.Base.BaseFragment
import com.example.ds_movies.R
import com.example.ds_movies.data.models.Genre
import com.example.ds_movies.databinding.FragmentMoviesTabBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesTabFragment : BaseFragment<FragmentMoviesTabBinding,MoviesTabViewModel>(R.layout.fragment_movies_tab) {

    override val viewModel: MoviesTabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGenresTabs()
    }

    private fun initGenresTabs() {
        viewModel.genresList.observe(viewLifecycleOwner, Observer {
            val list = it
            list.add(0, Genre(0,"All"))
            list.forEach { genre ->
                binding.genresTabs.addTab(binding.genresTabs.newTab().setText(genre.name).setTag(genre))
            }
        })

        binding.genresTabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
         binding.genresTabs.getTabAt(0)?.select()
    }

    override fun getViewBinding(v: View): FragmentMoviesTabBinding {
        return FragmentMoviesTabBinding.bind(v)
    }
}