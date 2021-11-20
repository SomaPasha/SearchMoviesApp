package space.kuz.searchmoviesapp.iu.main

import android.app.Application
import android.content.Context
import android.widget.Toast
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.data.WebTheMovieRepoImpl
import space.kuz.searchmoviesapp.domain.entity.MovieClass
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepo
import space.kuz.searchmoviesapp.implimentation.MovieRepositoryImplementation

class App: Application() {


    val theMovieRepo: TheMovieRepo by lazy { WebTheMovieRepoImpl() }
    var moviesRepo: MovieRepository = MovieRepositoryImplementation()
    var moviesRepoTwo: MovieRepository = MovieRepositoryImplementation()



    fun fillRepoCrazzy() {
        moviesRepoTwo.createMovie(
            MovieClass("https://thumbs.dfs.ivi.ru/storage5/contents/9/e/afef00f071174caaa41933f332524f.jpg",
            "Я Фильм про1","Duna", "2020",  6.9)
        )
        moviesRepoTwo.createMovie(
            MovieClass("https://www.kino-teatr.ru/movie/posters/big/9/130649.jpg",
            "Я Фильм про2","Dgek", "2019",  6.8)
        )
        moviesRepoTwo.createMovie(
            MovieClass("https://b1.filmpro.ru/c/812189.246x347.jpg",
            "Я Фильм про3","Doctor", "2021",  6.7)
        )
        moviesRepoTwo.createMovie(
            MovieClass("https://www.vokrug.tv/pic/product/5/3/4/b/534b3e682082993013e1c27f5d039e8b.jpg",
            "Я Фильм про4","Cosmos", "2018",  6.5)
        )
        moviesRepoTwo.createMovie(
            MovieClass("https://b1.filmpro.ru/c/814656.492x694.jpg",
            "Я Фильм про5","Rasomaxa", "2010",  6.4)
        )
    }

    fun fillRepoFant() {
        moviesRepo.createMovie(
            MovieClass("https://thumbs.dfs.ivi.ru/storage23/contents/b/1/5d70a511fcb260f2cb026dc0eeed08.jpg/234x360/",
            "Я Фильм про1","Duna", "2020",  6.9)
        )
        moviesRepo.createMovie(
            MovieClass("https://www.kino-teatr.ru/movie/posters/big/5/103415.jpg",
            "Я Фильм про2","Dgek", "2019",  6.8)
        )
        moviesRepo.createMovie(
            MovieClass("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqhV9LHusumPigMTEYZM_BLwpJKQLxWhMK3gZK4zes_S9MPyToyf-nEHujMN7GwlLjH9s&usqp=CAU",
            "Я Фильм про3","Doctor", "2021",  6.7)
        )
        moviesRepo.createMovie(
            MovieClass("https://upload.wikimedia.org/wikipedia/ru/6/65/Molly%27s_Game_%28film%29.jpg",
            "Я Фильм про4","Cosmos", "2018",  6.5)
        )
        moviesRepo.createMovie(MovieClass("https://thumbs.dfs.ivi.ru/storage8/contents/0/a/4bde2168ae0492042f253f6bffeda1.jpg/234x360/",
            "Я Фильм про5","Rasomaxa", "2010",  6.4))
    }
}

 val Context.app
get() = applicationContext as App