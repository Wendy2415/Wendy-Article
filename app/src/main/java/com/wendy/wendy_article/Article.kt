package com.wendy.wendy_article

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val category : String,
    val title: String,
    val date: String,
    val photo: String,
    val rate: String,
    val desc: String,
): Parcelable
