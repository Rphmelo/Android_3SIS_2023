package br.com.fiap.mymusiclibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.mymusiclibrary.database.AppDatabase
import br.com.fiap.mymusiclibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val musicAdapter by lazy {
        MusicAdapter()
    }
    private val appDb by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = musicAdapter

        binding.favButton.setOnClickListener {
            musicAdapter.setData(appDb.musicDao().selectBy(true))
        }

        binding.noFavButton.setOnClickListener {
            musicAdapter.setData(appDb.musicDao().selectBy(false))
        }
        setData()
    }

    private fun setData() {
        musicAdapter.setData(appDb.musicDao().selectAll())
    }
}