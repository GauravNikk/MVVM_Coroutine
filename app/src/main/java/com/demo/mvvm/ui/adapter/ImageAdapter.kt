package com.demo.mvvm.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.R
import com.demo.network.responseModels.home.ImageResponseModelItem
import kotlin.coroutines.coroutineContext

class ImageAdapter(private val photos: List<ImageResponseModelItem>) :
    RecyclerView.Adapter<ImageAdapter.PhotoViewHolder>() {

        private lateinit var context: Context

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewAuthor: TextView = view.findViewById(R.id.textViewAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        context=parent.context
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.textViewAuthor.text = photo.author
        Glide.with(context).load(photo.download_url).into(holder.imageView)

    }

    override fun getItemCount(): Int = photos.size
}
