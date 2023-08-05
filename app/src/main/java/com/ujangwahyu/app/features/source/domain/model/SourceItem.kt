package com.ujangwahyu.app.features.source.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SourceItem (
    val name: String?,
    val description: String?,
    val id: String?,
): Parcelable