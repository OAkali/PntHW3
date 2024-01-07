package com.example.pnt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pnt.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = ImageAdapter(arrayListOf())
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        binding.recyclerV.adapter = adapter

    }

    private fun initClickers() {
        with(binding) {
            btnSearch.setOnClickListener {
                getImage(1)
            }
            btnNext.setOnClickListener {
                page++
                getImage(page)
            }
        }
    }

    private fun ActivityMainBinding.getImage(page: Int) {
        progressBar.isVisible=true
        RetrofitService().api.getImage(etWord.text.toString(), page = page)
            .enqueue(object : Callback<PixelModel> {
                override fun onResponse(
                    call: Call<PixelModel>,
                    response: Response<PixelModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            progressBar.isVisible=false
                            adapter.addNewImage(it.hits as ArrayList<ImageModel>)
                        }

                    }
                }

                override fun onFailure(call: Call<PixelModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}
