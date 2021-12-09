package space.kuz.searchmoviesapp.data

import space.kuz.searchmoviesapp.domain.entity.MovieClassRoom
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepoRoom
import java.util.List

class MemoryCacheMovieRepoImpl:TheMovieRepoRoom {
    private  val cache: MutableList<MovieClassRoom> = mutableListOf()
    private var  counter =0

    override fun getMovie(): MutableList<MovieClassRoom> {
       return  cache
    }

    override fun createMovie(movie: MovieClassRoom): Int {
        var id = counter++
        cache.add(movie.copy(id=id))
        return id
    }

    override fun updateMovie(id: Int, movie: MovieClassRoom): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteMovie(id: Int): Boolean {
      val indexToDelete =   cache.indexOfFirst {
            it.id == id
        }
     cache.removeAt(indexToDelete)
        return true
    }
}