package space.kuz.searchmoviesapp.domain.repo

import space.kuz.searchmoviesapp.domain.entity.Movie

interface MovieRepository  {
    fun  getMovie():List<Movie>
    fun createMovie(movie: Movie):Long
    fun updateMovie(id:Long, movie: Movie):Boolean
    fun deleteMovie(id:Long):Boolean
}