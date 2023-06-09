package com.kevin.shyriapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.shyriapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ShyriAdapter
    lateinit var db: ExternalDB
    var shyriList = ArrayList<ShyriModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ShyriAdapter()

        db = ExternalDB(this, "shayari.db", null, 1)
        shyriList = db.shyri as ArrayList<ShyriModel>

        adapter.setShyri(shyriList)

        binding.rcvshyrilist.layoutManager = LinearLayoutManager(this)
        binding.rcvshyrilist.adapter = adapter

    }
}