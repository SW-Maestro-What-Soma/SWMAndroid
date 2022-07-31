package com.example.swmandroid.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemBookBinding
import com.example.swmandroid.model.book.BookItem

class BookAdapter(
    private val bookList: ArrayList<BookItem>,
    private val clickListener : (BookItem) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.bookImageview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BookViewHolder(ItemBookBinding.bind(view))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = bookList[position]
        holder.img.setImageResource(item.img)
        holder.img.setOnClickListener {
            clickListener(bookList[position])
        }
    }

    override fun getItemCount(): Int = bookList.size
}