package com.example.peternakan

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import DataArray.Arrayy
import DataHewan.Hewan
import androidx.activity.result.contract.ActivityResultContracts
import com.example.peternakan.databinding.ActivityAddBinding
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddBinding
    private lateinit var binatang: Hewan
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLEY
            viewBinding.addgambar.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.hide()
        edit()
        listener()
        returnn()
    }

    private fun returnn() {
        imagebackButton.setOnClickListener(){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun edit(){
        var position = intent.getIntExtra("position", -1)
        if(position != -1){
            val animal = Arrayy.Hewann[position]
            viewBinding.TambahHewan.text = "Edit movie"
            viewBinding.Tombol.text = "EDIT HEWAN"
            viewBinding.namahewan.editText?.setText(animal.nama)
            viewBinding.jenishewan.editText?.setText(animal.jenis)
            viewBinding.usiahewan.editText?.setText(animal.usia.toString())
            viewBinding.addgambar.setImageURI(Uri.parse(Arrayy.Hewann[position].image))
        }
    }
    private fun listener(){
        viewBinding.addgambar.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.Tombol.setOnClickListener{
            var nama = viewBinding.namahewan.editText?.text.toString().trim()
            var jenis = viewBinding.jenishewan.editText?.text.toString().trim()
            var usia = 0

            binatang = Hewan(nama, jenis, usia)
            checker()
        }

    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(binatang.nama!!.isEmpty()){
            viewBinding.namahewan.error = "Nama Hewan Tidak Boleh Kosong"
            isCompleted = false
        }else{
            viewBinding.namahewan.error = ""
        }

        if(binatang.jenis!!.isEmpty()){
            viewBinding.jenishewan.error = "Jenis Hewan Tidak Boleh Kosong!"
            isCompleted = false
        }else{
            viewBinding.jenishewan.error = ""
        }

        binatang.image = image.toString()

        if(viewBinding.usiahewan.editText?.text.toString().isEmpty())
        {
            viewBinding.usiahewan.error = "Umur Hewan Tidak Boleh Kosong!"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                binatang.usia = viewBinding.usiahewan.editText?.text.toString().toInt()
                Arrayy.Hewann.add(binatang)

            }else
            {
                binatang.usia = viewBinding.usiahewan.editText?.text.toString().toInt()
                Arrayy.Hewann[position] = binatang
            }
            finish()
        }
    }
}