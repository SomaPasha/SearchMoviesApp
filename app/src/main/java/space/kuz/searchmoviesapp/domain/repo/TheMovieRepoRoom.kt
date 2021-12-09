
package space.kuz.searchmoviesapp.domain.repo;

import java.util.List;

import space.kuz.searchmoviesapp.domain.entity.MovieClassRoom;

interface TheMovieRepoRoom {
    fun  getMovie():MutableList<MovieClassRoom>
    fun createMovie(movie: MovieClassRoom):Int
    fun updateMovie(id:Int, movie: MovieClassRoom):Boolean
    fun deleteMovie(id:Int):Boolean
}