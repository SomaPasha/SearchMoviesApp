package space.kuz.searchmoviesapp.data

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import space.kuz.searchmoviesapp.domain.entity.MovieClass
import space.kuz.searchmoviesapp.domain.entity.Root
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepo
import space.kuz.searchmoviesapp.iu.main.App
import space.kuz.searchmoviesapp.iu.main.MainActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class WebTheMovieRepoImpl:TheMovieRepo {

    override fun getReposForUserSync(): List<MovieClass> {

         val result= emptyList<MovieClass>().toMutableList()
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(themoviedbURl)
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connectTimeout = 5_000

                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                var resultJson = bufferedReader.readLines().toString()

                var model = gson.fromJson(resultJson, Array<Root>::class.java)

                model.forEach {
                    it.results.forEach {
                        result.add(MovieClass(
                            "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces" +
                                    it.image,
                            it.name, it.description, it.year.substring(0, 4), it.rating
                        ))

                    }

                }


            } catch ( e:Exception){

            }
            finally {
                urlConnection?.disconnect()
            }
        return  result
    }
    val themoviedbURl: String =  "https://api.themoviedb.org/3/discover/movie?api_key=b394bdee20e1f534a09fb18b1a16568a&with_genres=27"
    val gson by lazy { Gson() }
    override fun getReposForUserAsync( callback: (List<MovieClass>) -> Unit) {
       Thread {
       callback.invoke(getReposForUserSync())
       }.start()
    }

}