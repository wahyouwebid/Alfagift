package com.ujangwahyu.app.features.source.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ujangwahyu.app.features.source.domain.model.SourceItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSource(

	@field:SerializedName("country")
	val country: String?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("language")
	val language: String?,

	@field:SerializedName("id")
	val id: String?,

	@field:SerializedName("category")
	val category: String?,

	@field:SerializedName("url")
	val url: String?
): Parcelable {
	fun toDomain(): SourceItem {
		return SourceItem(
			name = this.name ?: "",
			description = this.description ?: "",
			id = this.id ?: ""
		)
	}
}