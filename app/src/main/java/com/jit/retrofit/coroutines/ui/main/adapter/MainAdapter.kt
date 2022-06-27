package com.jit.retrofit.coroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jit.retrofit.coroutines.R
import com.jit.retrofit.coroutines.data.model.Movie
import com.jit.retrofit.coroutines.ui.main.adapter.MainAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_layout.view.imageViewAvatar
import kotlinx.android.synthetic.main.item_layout.view.textViewUserEmail
import kotlinx.android.synthetic.main.item_layout.view.textViewUserName

class MainAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Movie) {
            itemView.apply {
                textViewUserName.text = user.title
                textViewUserEmail.text = user.imdbID
                Glide.with(imageViewAvatar.context)
                    .load(user.poster)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }

    }

    fun clearAllData()
    {
        this.movies.apply {
            clear()
        }
    }
}