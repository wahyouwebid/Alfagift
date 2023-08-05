package com.ujangwahyu.app.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ujangwahyu.app.features.news.domain.model.NewsItem
import com.ujangwahyu.app.features.news.domain.model.SourceItem
import com.ujangwahyu.app.features.search.domain.model.SearchItem
import com.ujangwahyu.app.features.search.domain.model.SourceSearchItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataNews(
    @SerializedName("source")
    var modelSource: Source?,

    @SerializedName("author")
    var author: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("description")
    var description: String? = "",

    @SerializedName("url")
    var url: String? = "",

    @SerializedName("urlToImage")
    var urlToImage: String? = "",

    @SerializedName("publishedAt")
    var publishedAt: String? = "",

    @SerializedName("content")
    var content: String? = ""

) : Parcelable {
    fun toNewsDomain(): NewsItem {

        val source = SourceItem(
            this.modelSource?.id,
            this.modelSource?.name,
        )

        return NewsItem(
            modelSource = source,
            author = this.author,
            title = this.title,
            description = this.description,
            url = this.url,
            urlToImage = this.urlToImage,
            publishedAt = this.publishedAt,
            content = this.content,
        )
    }

    fun toSearchDomain(): SearchItem {

        val source = SourceSearchItem(
            this.modelSource?.id,
            this.modelSource?.name,
        )

        return SearchItem(
            modelSource = source,
            author = this.author,
            title = this.title,
            description = this.description,
            url = this.url,
            urlToImage = this.urlToImage,
            publishedAt = this.publishedAt,
            content = this.content,
        )
    }
}
