package com.jit.retrofit.coroutines.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jit.retrofit.coroutines.R.*
import com.jit.retrofit.coroutines.data.api.ApiHelper
import com.jit.retrofit.coroutines.data.api.RetrofitBuilder
import com.jit.retrofit.coroutines.data.model.Movie
import com.jit.retrofit.coroutines.ui.base.ViewModelFactory
import com.jit.retrofit.coroutines.ui.main.adapter.MainAdapter
import com.jit.retrofit.coroutines.ui.main.viewmodel.MainViewModel
import com.jit.retrofit.coroutines.utils.Status.ERROR
import com.jit.retrofit.coroutines.utils.Status.LOADING
import com.jit.retrofit.coroutines.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }



    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        btn_search.setOnClickListener(View.OnClickListener {

            adapter.clearAllData()
            setupObservers(edt_search.text.toString())

        })
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
               when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                                //retrieveList(it.resultSearch)
                            println("${it.resultSearch?.size}!!")
                            println("${it.resultSearch?.size}!!")
                            retrieveList(it.resultSearch!!)
                        }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupObservers(searchString : String) {
        viewModel.getUsersSearch(searchString).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            //retrieveList(it.resultSearch)
                            println("${it.resultSearch?.size}!!")
                            println("${it.resultSearch?.size}!!")
                            retrieveList(it.resultSearch!!)
                        }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(movies: List<Movie>) {

        adapter.apply {
            addMovies(movies)
            notifyDataSetChanged()
        }
    }
}
