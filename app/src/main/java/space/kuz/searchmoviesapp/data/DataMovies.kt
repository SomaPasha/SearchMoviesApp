package space.kuz.searchmoviesapp.data

import android.net.Uri
import com.mifmif.common.regex.Main
import space.kuz.searchmoviesapp.iu.main.MainActivity
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class DataMovies {
    private val themoviedbURl: String =  "https://api.themoviedb.org/3/discover/movie?api_key=b394bdee20e1f534a09fb18b1a16568a&with_genres=27"

    fun  start(){

        Thread {
            var result = ""
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(themoviedbURl)
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connectTimeout = 5_000

                // val out: OutputStream = BufferedOutputStream(urlConnection.outputStream)
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                result = bufferedReader.readLines().toString()

            } finally {
                urlConnection?.disconnect()
            }


        }.start()

    }
}