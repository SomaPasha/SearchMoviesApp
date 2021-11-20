package space.kuz.searchmoviesapp.implimentation

import space.kuz.searchmoviesapp.domain.entity.MovieClass
import space.kuz.searchmoviesapp.domain.repo.MovieRepository

class MovieRepositoryImplementation() : MovieRepository {
     private var  moviesArr:ArrayList<MovieClass> = ArrayList()
     private  var counter:Long=0
    override fun getMovie(): List<MovieClass> {
        return ArrayList<MovieClass>(moviesArr)
    }

    override fun createMovie(movie: MovieClass): Long {
        movie.id=++counter
        moviesArr.add(movie)
        return counter
    }

    override fun updateMovie(id: Long, movie: MovieClass): Boolean {
        deleteMovie(id)
        movie.id=id
        moviesArr.add(id.toInt()-1,movie)
        return true
    }

    override fun deleteMovie(id: Long): Boolean {
        for (i in 0..moviesArr.size){
            if(id == moviesArr.get(i).id){
                moviesArr.removeAt(i)
                return true
            }
        }
        return false
    }
}