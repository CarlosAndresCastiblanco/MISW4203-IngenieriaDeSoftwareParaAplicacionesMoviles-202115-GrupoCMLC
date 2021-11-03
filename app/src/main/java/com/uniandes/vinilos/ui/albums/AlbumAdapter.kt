package com.uniandes.vinilos.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uniandes.vinilos.R
import kotlinx.android.synthetic.main.item_album.view.*

import com.uniandes.vinilos.data.model.AlbumModel

class AlbumAdapter (
    val albums:  List<AlbumModel>
        ):RecyclerView.Adapter<AlbumAdapter.AlbumHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  AlbumHolder(layoutInflater.inflate(R.layout.item_album, parent,false))
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.render(albums[position])
    }

    override fun getItemCount(): Int =albums.size

    class AlbumHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(album: AlbumModel){
            view.tvAlbumName.text = album.name
            view.tvAlbumDescription.text = album.description
            view.tvAlbumArtist.text = album.recordLabel
            Picasso.get().load(album.cover).into(view.imageAlbum)
        }
    }
}

