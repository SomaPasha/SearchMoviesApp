package space.kuz.searchmoviesapp.domain.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val  MOVIE_TABLE_NAME = "movies"

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieClassRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image")
    val image: String,// image
    @ColumnInfo(name = "name")
    val name:String,// название
    @ColumnInfo(name = "description")
    val description:String,// description
    @ColumnInfo(name = "year")
    val year:String, // дата
    @ColumnInfo(name = "rating")
    val rating:Double
)
