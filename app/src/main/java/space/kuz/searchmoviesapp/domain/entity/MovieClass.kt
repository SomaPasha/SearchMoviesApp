package space.kuz.searchmoviesapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.concurrent.atomic.AtomicLong

@Parcelize
data class MovieClass (
   //  val id :Long,
   // val poster_path: String,
 //   @SerializedName("title")
  //  val title:Long
  //  val original_title: String,

 @SerializedName("backdrop_path")
 val image: String,// image
 @SerializedName("original_title")
 val name:String,// название
 @SerializedName("overview")
 val   description:String,// description
 @SerializedName("release_date")
 val  year:String, // дата
 @SerializedName("vote_average")
 val  rating:Double

) : Parcelable {
 var id : Long? = null
}

