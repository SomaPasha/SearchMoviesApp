package space.kuz.searchmoviesapp.data

import android.content.Context
import androidx.room.Room
import space.kuz.searchmoviesapp.domain.entity.MovieClassRoom
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepoRoom

class RoomMovieRepoImpl(context: Context): TheMovieRepoRoom{
    private  val movieDao: MovieDao =
        Room.databaseBuilder(context, MovieRoomBd::class.java, "movies.db").build().movieDao()



    override fun getMovie(): MutableList<MovieClassRoom> {
    return  movieDao.getMovies()
    }

    override fun createMovie(movie: MovieClassRoom): Int {
        movieDao.add(movie)
        return  -1
    }

    override fun updateMovie(id: Int, movie: MovieClassRoom): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteMovie(id: Int): Boolean {
     // todo movieDao.delete()
        return true
    }
}