package com.ujangwahyu.app.features.search.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.common.utils.dateFormat
import com.ujangwahyu.app.common.utils.dateTimeHourAgo
import com.ujangwahyu.app.common.utils.loadImage
import com.ujangwahyu.app.databinding.AdapterSearchBinding
import com.ujangwahyu.app.features.search.domain.model.SearchItem

class SearchAdapter(
    private val showDetail: (SearchItem?) -> Unit
) : PagingDataAdapter<SearchItem, SearchAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: AdapterSearchBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val item = getItem(position)
            if (item?.author.isNullOrEmpty()) {
                tvSourceName.text = item?.modelSource?.name
            } else {
                tvSourceName.text = item?.author + " \u2022 " + item?.modelSource?.name
            }

            tvTitle.text = item?.title

            if(item?.publishedAt != null){
                val dateTime = item.publishedAt?.substring(0, 19)
                tvDateTime.text = dateTime.dateFormat()
                tvTime.text = dateTime.dateTimeHourAgo()
            }

            ivThumbnail.loadImage(item?.urlToImage)

            root.setOnClickListener {
                showDetail.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    object DiffCallback : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }
}