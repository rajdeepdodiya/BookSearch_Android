package com.example.booksearch.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearch.R
import com.example.booksearch.databinding.RowLayoutBinding
import com.example.booksearch.models.BookListModel
import com.example.booksearch.models.Items
import com.example.booksearch.models.VolumeInfo

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    var bookList = ArrayList<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class BookListViewHolder(view: RowLayoutBinding): RecyclerView.ViewHolder(view.root){

        private val tvBookTitle = view.tvBookTitle
        private val tvAuthor = view.tvAuthor
        private val tvDescription = view.tvDescription
        private val ivBookImage = view.ivBookImage

        fun bind(data: Items){
            tvBookTitle.text = data.volumeInfo?.title
            tvAuthor.text = data.volumeInfo?.authors?.let {
                    TextUtils.join(", ", it)
            }?: ""
            tvDescription.text = data.volumeInfo?.description

            data.volumeInfo?.imageLinks?.smallThumbnail.let{
                Glide.with(ivBookImage)
                    .load(it)
                    .circleCrop()
                    .into(ivBookImage)
            }
        }
    }
}