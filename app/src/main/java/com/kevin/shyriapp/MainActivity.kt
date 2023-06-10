package com.kevin.shyriapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.shyriapp.databinding.ActivityMainBinding
import com.kevin.shyriapp.demo.database.ExternalDB

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ShyriAdapter
//    lateinit var db: ExternalDB
    lateinit var DatabaseHelper : ExternalDB
    var shyriList = ArrayList<ShyriModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ShyriAdapter()

        DatabaseHelper = ExternalDB(this, "shayari.db", 5)
            this.DatabaseHelper.CheckDb()
        shyriList = DatabaseHelper.shyri as ArrayList<ShyriModel>

        adapter.setShyri(shyriList)

        binding.rcvshyrilist.layoutManager = LinearLayoutManager(this)
        binding.rcvshyrilist.adapter = adapter

    }
}