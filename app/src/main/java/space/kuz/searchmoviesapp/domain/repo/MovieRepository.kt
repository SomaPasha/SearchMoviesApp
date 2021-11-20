package space.kuz.searchmoviesapp.domain.repo

import space.kuz.searchmoviesapp.domain.entity.MovieClass

interface MovieRepository  {
    fun  getMovie():List<MovieClass>
    fun createMovie(movie: MovieClass):Long
    fun updateMovie(id:Long, movie: MovieClass):Boolean
    fun deleteMovie(id:Long):Boolean
}