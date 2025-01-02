package com.demo.mvvm.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.databinding.ActivityHomeBinding
import com.demo.mvvm.factories.HomeViewModelFactory
import com.demo.mvvm.ui.adapter.ImageAdapter
import com.demo.mvvm.viewmodel.HomeViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity() , KodeinAware {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override val kodein by kodein()
    private val homeViewModelFactory : HomeViewModelFactory by instance()

    private var isLoading = false
    private var currentPage = 1
    private val pageSize = 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        binding.homeLayoutViewModel = homeViewModel



//        homeViewModel.updateImageData(listOf())

        val layoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.isHardwareAccelerated
        binding.recyclerView.setItemViewCacheSize(100000)


        homeViewModel.imageData.observe(this){
            binding.recyclerView.adapter = ImageAdapter(it)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5) &&
                    firstVisibleItemPosition >= 0
                ) {
                    loadImages()
                }
            }
        })




    }

    fun loadImages() {
       homeViewModel.getImages((currentPage++).toString(),"20")
    }
}