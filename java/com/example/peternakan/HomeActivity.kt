package com.example.peternakan

import Adapter.AdapterPeternakann
import Interface.InterfacePeternakan
import DataArray.Arrayy
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.peternakan.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), InterfacePeternakan{
    private lateinit var viewBinding:ActivityHomeBinding
    private val adapter = AdapterPeternakann(Arrayy.Hewann, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        listener()
    }


    private fun listener(){
        viewBinding.floatingaddbutton.setOnClickListener {
            val myIntent = Intent(this, AddActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this,1)
        viewBinding.list.layoutManager = layoutManager
        viewBinding.list.adapter = adapter
    }

    override fun onCardClick(position: Int) {
        TODO("Not yet implemented")
    }
}
