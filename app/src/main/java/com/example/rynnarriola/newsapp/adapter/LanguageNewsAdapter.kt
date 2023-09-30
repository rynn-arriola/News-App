package com.example.rynnarriola.newsapp.adapter

import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.databinding.ItemLanguageNewsBinding


class LanguageNewsAdapter(
    private val articleList: ArrayList<LanguageSource>
) : RecyclerView.Adapter<LanguageNewsAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemLanguageNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: LanguageSource) {
            binding.name.text = article.name
            binding.name.setTypeface(null, Typeface.BOLD)
            binding.description.text = article.description
            binding.url.text = article.url
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLanguageNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<LanguageSource>) {
        articleList.addAll(list)
    }

}