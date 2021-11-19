package space.kuz.searchmoviesapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Results (
   //  val id :Long,
   // val poster_path: String,
 //   @SerializedName("title")
  //  val title:Long
  //  val original_title: String,

 val adult :Boolean,
 val backdrop_path: String,
  val genre_ids:List<Long>,
 val id : Long,
 val original_language:String,
 val original_title:String,
 val  overview:String,
 val popularity:Double,
 val  poster_path:String,
 val  release_date:String,
 val  title:String,
 val boolean :Boolean,
 val  vote_average:Double,
 val  vote_count:Long

)

