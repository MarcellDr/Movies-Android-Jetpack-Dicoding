package com.marcelldr.cinemaz.data.repository.sources.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(
    val id: Int,
    val title: String,
    val genre: String,
    val rating: Double,
    val overview: String,
    val poster: String,
    val background: String
) : Parcelable