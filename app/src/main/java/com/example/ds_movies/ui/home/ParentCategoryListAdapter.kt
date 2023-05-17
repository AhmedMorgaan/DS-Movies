package com.example.ds_movies.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ds_movies.R
import com.example.ds_movies.data.models.CategoryResponse
import kotlinx.coroutines.runBlocking

class ParentCategoryListAdapter (
    private val parentCategoryList :MutableList<CategoryResponse.Genre>
    ,private val viewModel: MainCategoryViewModel
): RecyclerView.Adapter<ParentCategoryListAdapter.ViewHolder>() {

     private lateinit var  adapter : ChildCategoryListAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = parentCategoryList[position]
       val moviesList =  viewModel.getMoviesWithId(item.id)
        holder.title.text = item.name

        runBlocking {
            adapter = ChildCategoryListAdapter(moviesList.await())
            Log.e("adapter",moviesList.await().toString())
        }
        holder.recyclerView.setHasFixedSize(true)
        holder.recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
       return parentCategoryList.size?:0
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val title : TextView
        val recyclerView : RecyclerView
        init {
            title = itemView.findViewById(R.id.parent_txt_title)
            recyclerView = itemView.findViewById(R.id.category_child_recyclerview)
        }
    }
}