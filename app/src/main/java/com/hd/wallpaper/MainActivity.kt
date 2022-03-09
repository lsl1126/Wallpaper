package com.hd.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hd.wallpaper.adapter.PhotoAdapter
import com.hd.wallpaper.databinding.ActivityMainBinding
import com.hd.wallpaper.itemDecoration.SpacesItemDecoration
import com.hd.wallpaper.viewModel.PhotoViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var photoAdapter: PhotoAdapter

    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    private fun initView() {
        photoAdapter = PhotoAdapter(this)
        binding.apply {
            listPhoto.adapter = photoAdapter
            listPhoto.layoutManager = GridLayoutManager(this@MainActivity, 3)
            listPhoto.addItemDecoration(SpacesItemDecoration(20))
            listPhoto.setPadding(10, 0, 10, 0)
            listPhoto.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Glide.with(this@MainActivity).resumeRequests()
                    } else {
                        Glide.with(this@MainActivity).pauseRequests()
                    }
                }
            })
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.getPhotoData().collectLatest {
                photoAdapter.submitData(it)
            }
        }
    }
}