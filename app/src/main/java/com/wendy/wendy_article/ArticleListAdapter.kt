package com.wendy.wendy_article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wendy.wendy_article.databinding.RowListArticleBinding

class ArticleListAdapter(private val articlelist: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleListAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = RowListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = articlelist.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (category, titles, date, photo, rate) = articlelist[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemCategory.text = category
        holder.binding.tvItemTitle.text = titles
        holder.binding.tvItemDate.text = date
        holder.binding.tvItemRate.text = rate
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(articlelist[holder.adapterPosition])
        }
    }

    class ListViewHolder(var binding: RowListArticleBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

}
