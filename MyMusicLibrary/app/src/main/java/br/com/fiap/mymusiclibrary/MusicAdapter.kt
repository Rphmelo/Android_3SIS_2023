package br.com.fiap.mymusiclibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.mymusiclibrary.database.MusicModel
import br.com.fiap.mymusiclibrary.databinding.ViewMusicItemBinding

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    private val musicList: MutableList<MusicModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ViewMusicItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_music_item,
                parent,
                false
            )
        )
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bindView( musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun setData(list: List<MusicModel>) {
        musicList.clear()
        musicList.addAll(list)

        notifyDataSetChanged()
    }

    inner class MusicViewHolder(
        private val view: ViewMusicItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(item: MusicModel) {
            view.musicTitle.text = item.title
            view.musicArtist.text = item.artist
            view.favorite.text = if(item.favorite) "Favorita" else ""
        }
    }
}