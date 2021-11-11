package space.kuz.searchmoviesapp.iu.main

import android.app.Application
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.implimentation.MovieRepositoryImplementation

class App: Application() {
    var moviesRepo: MovieRepository = MovieRepositoryImplementation()
    var moviesRepoTwo: MovieRepository = MovieRepositoryImplementation()



    fun fillRepoCrazzy() {
        moviesRepoTwo.createMovie(Movie(R.drawable.rasomaxa, "Я Фильм про1","Duna", "2020",  6.9F))
        moviesRepoTwo.createMovie(Movie(R.drawable.kosmos,"Я Фильм про2","Dgek", "2019",  6.8F))
        moviesRepoTwo.createMovie(Movie(R.drawable.dgek, "Я Фильм про3","Doctor", "2021",  6.7F))
        moviesRepoTwo.createMovie(Movie(R.drawable.doctor,"Я Фильм про4","Cosmos", "2018",  6.5F))
        moviesRepoTwo.createMovie(Movie(R.drawable.duna_image,"Я Фильм про5","Rasomaxa", "2010",  6.4F))
    }

    fun fillRepoFant() {
        moviesRepo.createMovie(Movie(R.drawable.duna_image, "Я Фильм про1","Duna", "2020",  6.9F))
        moviesRepo.createMovie(Movie(R.drawable.dgek,"Я Фильм про2","Dgek", "2019",  6.8F))
        moviesRepo.createMovie(Movie(R.drawable.doctor, "Я Фильм про3","Doctor", "2021",  6.7F))
        moviesRepo.createMovie(Movie(R.drawable.kosmos,"Я Фильм про4","Cosmos", "2018",  6.5F))
        moviesRepo.createMovie(Movie(R.drawable.rasomaxa,"Я Фильм про5","Rasomaxa", "2010",  6.4F))
    }
}