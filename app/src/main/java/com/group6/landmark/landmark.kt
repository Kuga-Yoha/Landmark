package com.group6.landmark

import android.os.Parcel
import android.os.Parcelable

data class Landmark(
    val name: String,
    val type: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val imagePlaceholder: Int // Placeholder for landmark image
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(address)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeInt(imagePlaceholder)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Landmark> {
        override fun createFromParcel(parcel: Parcel): Landmark {
            return Landmark(parcel)
        }

        override fun newArray(size: Int): Array<Landmark?> {
            return arrayOfNulls(size)
        }
    }
}
