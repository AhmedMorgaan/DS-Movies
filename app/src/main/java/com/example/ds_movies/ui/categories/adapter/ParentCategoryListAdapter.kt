package com.example.ds_movies.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ds_movies.R
import com.example.ds_movies.data.models.Genre
import com.example.ds_movies.ui.categories.MainCategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParentCategoryListAdapter (
    private val parentCategoryList :MutableList<Genre>
    ,private val viewModel: MainCategoryViewModel
): RecyclerView.Adapter<ParentCategoryListAdapter.ViewHolder>() {

//     private lateinit var  adapter : ChildCategoryListAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = parentCategoryList[position]
        GlobalScope.launch(Dispatchers.IO) {
            if(position == 0){
                val popularMovies = viewModel.getPopularMovies()
                val popularAdapter = ChildCategoryListAdapter(popularMovies)
                withContext(Dispatchers.Main) {
                    holder.recyclerView.adapter = popularAdapter
                    holder.title.text = item.name
                    holder.recyclerView.setHasFixedSize(true)
                }
            }
            else {
                val moviesList = viewModel.getMoviesWithCategoryId(item.id)
                val adapter = ChildCategoryListAdapter(moviesList)
                withContext(Dispatchers.Main) {
                    holder.recyclerView.adapter = adapter
                }
            }
        }
        holder.title.text = item.name
        holder.recyclerView.setHasFixedSize(true)
        holder.seeMore.setOnClickListener {
            onItemClickListener?.onItemClick(position, item)
        }
    }

    var onItemClickListener : OnItemClickListener? = null
    interface OnItemClickListener{
        fun onItemClick(pos:Int,item:Genre)
    }

    override fun getItemCount(): Int {
       return parentCategoryList.size?:0

    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val seeMore :TextView
        val title : TextView
        val recyclerView : RecyclerView
        init {
            seeMore = itemView.findViewById(R.id.txt_see_all)
            title = itemView.findViewById(R.id.parent_txt_title)
            recyclerView = itemView.findViewById(R.id.category_child_recyclerview)
        }
    }
}