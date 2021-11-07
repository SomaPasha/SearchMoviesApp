package space.kuz.searchmoviesapp.domain.entity

import android.media.Image
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val image:String,
    val description:String,
    val name: String,
    val year: String,
    val rating: Float
    ):Parcelable