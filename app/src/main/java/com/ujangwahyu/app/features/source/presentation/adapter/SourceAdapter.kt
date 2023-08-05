package com.ujangwahyu.app.features.source.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.databinding.AdapterSourceBinding
import com.ujangwahyu.app.features.source.domain.model.SourceItem

class SourceAdapter (
    private val showDetail: (SourceItem) -> Unit
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    private var data = ArrayList<SourceItem>()

    fun setData(itemList: List<SourceItem>?) {
        if (itemList.isNullOrEmpty()) return
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.name
            tvDescription.text = item.description
            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterSourceBinding) : RecyclerView.ViewHolder(view.root)

}