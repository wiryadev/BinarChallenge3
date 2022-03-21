package com.wiryadev.binarchallenge3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PotentialEnergy(
    val mass: Double,
    val gravity: Double,
    val height: Int,
) : Parcelable
