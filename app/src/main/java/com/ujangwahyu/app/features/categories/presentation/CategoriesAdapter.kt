package com.ujangwahyu.app.features.categories.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.common.utils.getDrawableIdFromFileName
import com.ujangwahyu.app.databinding.AdapterCategoryBinding
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem

class CategoriesAdapter (
    private val showDetail: (CategoriesItem) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var data = ArrayList<CategoriesItem>()

    fun setData(itemList: List<CategoriesItem>?) {
        if (itemList.isNullOrEmpty()) return
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.title
            ivThumbnail.setImageResource(ivThumbnail.getDrawableIdFromFileName(item.icon))
            root.setBackgroundResource(ivThumbnail.getDrawableIdFromFileName(item.color))
            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterCategoryBinding) : RecyclerView.ViewHolder(view.root)

}