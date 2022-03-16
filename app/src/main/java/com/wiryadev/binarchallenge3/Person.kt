package com.wiryadev.binarchallenge3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val age: Int,
    val address: String,
    val job: String,
) : Parcelable
