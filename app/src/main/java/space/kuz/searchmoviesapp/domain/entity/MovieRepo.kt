package space.kuz.searchmoviesapp.domain.entity

import com.google.gson.annotations.SerializedName

data class MovieRepo (
   //  val id :Long,
   // val poster_path: String,
    @SerializedName("title")
    val title:String,
  //  val original_title: String,
)