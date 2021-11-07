package space.kuz.searchmoviesapp.domain.repo

import space.kuz.searchmoviesapp.domain.entity.Movie

interface MovieRepository {
    fun  getMovie():List<Movie>
    fun createMovie(movie: Movie)
    fun updateMovie(id:Long, movie: Movie)
    fun deleteMovie(id:Long)
}