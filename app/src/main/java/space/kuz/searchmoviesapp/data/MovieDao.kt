package space.kuz.searchmoviesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import space.kuz.searchmoviesapp.domain.entity.MOVIE_TABLE_NAME
import space.kuz.searchmoviesapp.domain.entity.MovieClassRoom

@Dao
interface MovieDao {
    @Insert
    fun add(movie: MovieClassRoom)
    @Delete
    fun delete(movie: MovieClassRoom)
    @Query("SELECT * FROM ${MOVIE_TABLE_NAME}")
    fun getMovies():MutableList<MovieClassRoom>
    @Query("DELETE  FROM ${MOVIE_TABLE_NAME}")
    fun clear()
}