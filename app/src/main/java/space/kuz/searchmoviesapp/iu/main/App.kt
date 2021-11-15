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
        moviesRepoTwo.createMovie(Movie("https://thumbs.dfs.ivi.ru/storage5/contents/9/e/afef00f071174caaa41933f332524f.jpg",
            "Я Фильм про1","Duna", "2020",  6.9F))
        moviesRepoTwo.createMovie(Movie("https://www.kino-teatr.ru/movie/posters/big/9/130649.jpg",
            "Я Фильм про2","Dgek", "2019",  6.8F))
        moviesRepoTwo.createMovie(Movie("https://b1.filmpro.ru/c/812189.246x347.jpg",
            "Я Фильм про3","Doctor", "2021",  6.7F))
        moviesRepoTwo.createMovie(Movie("https://www.vokrug.tv/pic/product/5/3/4/b/534b3e682082993013e1c27f5d039e8b.jpg",
            "Я Фильм про4","Cosmos", "2018",  6.5F))
        moviesRepoTwo.createMovie(Movie("https://b1.filmpro.ru/c/814656.492x694.jpg",
            "Я Фильм про5","Rasomaxa", "2010",  6.4F))
    }

    fun fillRepoFant() {
        moviesRepo.createMovie(Movie("https://thumbs.dfs.ivi.ru/storage23/contents/b/1/5d70a511fcb260f2cb026dc0eeed08.jpg/234x360/",
            "Я Фильм про1","Duna", "2020",  6.9F))
        moviesRepo.createMovie(Movie("https://www.kino-teatr.ru/movie/posters/big/5/103415.jpg",
            "Я Фильм про2","Dgek", "2019",  6.8F))
        moviesRepo.createMovie(Movie("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqhV9LHusumPigMTEYZM_BLwpJKQLxWhMK3gZK4zes_S9MPyToyf-nEHujMN7GwlLjH9s&usqp=CAU",
            "Я Фильм про3","Doctor", "2021",  6.7F))
        moviesRepo.createMovie(Movie("https://upload.wikimedia.org/wikipedia/ru/6/65/Molly%27s_Game_%28film%29.jpg",
            "Я Фильм про4","Cosmos", "2018",  6.5F))
        moviesRepo.createMovie(Movie("https://thumbs.dfs.ivi.ru/storage8/contents/0/a/4bde2168ae0492042f253f6bffeda1.jpg/234x360/",
            "Я Фильм про5","Rasomaxa", "2010",  6.4F))
    }
}