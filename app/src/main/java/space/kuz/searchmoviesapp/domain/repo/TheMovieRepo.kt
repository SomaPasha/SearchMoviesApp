package space.kuz.searchmoviesapp.domain.repo

import androidx.annotation.WorkerThread
import space.kuz.searchmoviesapp.domain.entity.MovieClass
import javax.security.auth.callback.Callback

interface TheMovieRepo {
    @WorkerThread
     fun getReposForUserSync():List<MovieClass>
    fun getReposForUserAsync(callback: (List<MovieClass>) -> Unit)
}