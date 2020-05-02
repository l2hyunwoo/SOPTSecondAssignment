package com.example.soptsecondseminarrepeat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_bookname = itemView.findViewById<TextView>(R.id.tv_bookname)
    val img_book = itemView.findViewById<ImageView>(R.id.img_book)

    fun bind(bookData: BookData){
        tv_bookname.text = bookData.bookName
        Glide.with(itemView).load(bookData.img_book).into(img_book)
    }
}