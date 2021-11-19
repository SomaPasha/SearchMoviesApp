package space.kuz.searchmoviesapp.iu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.data.DataMovies
import space.kuz.searchmoviesapp.databinding.ActivityMainBinding
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.domain.entity.Results
import space.kuz.searchmoviesapp.domain.entity.Root
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.iu.MoviesAdapter
import space.kuz.searchmoviesapp.iu.fragment.ListMovieFragment
import space.kuz.searchmoviesapp.iu.fragment.OneMovieFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ListMovieFragment.Controller,
    OneMovieFragment.Controller {
    private val themoviedbURl: String =  "https://api.themoviedb.org/3/discover/movie?api_key=b394bdee20e1f534a09fb18b1a16568a&with_genres=27"
    private  val gson by lazy { Gson() }
    private lateinit var  binding: ActivityMainBinding
    var recyclerView: RecyclerView? = null
    var recyclerViewTwo: RecyclerView? = null
    var adapter: MoviesAdapter = MoviesAdapter()
    var adapterTwo: MoviesAdapter = MoviesAdapter()
    var dataMovie:DataMovies = DataMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Thread {

            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(themoviedbURl)
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connectTimeout = 5_000

                // val out: OutputStream = BufferedOutputStream(urlConnection.outputStream)
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

               var result = bufferedReader.readLines().toString()

                  //  val resJson =  gson.fromJson<MovieRepo>(result,  MovieRepo ::class.java)
           //     val gson = GsonBuilder().create()
               var model = gson.fromJson(result,Array<Results> ::class.java )
            //    val resJson = gson.fromJson(result , Root.)
    //    var jsonObject = JSONObject(result)
      //          var jsonArray = jsonObject.getJSONArray("results")
               // val groupListType: Type? = TypeToken<ArrayList<Results>>().type
                val sb = StringBuilder()

                model.forEach {

                    sb.appendLine(it.toString())
                }
                runOnUiThread {

                    binding.textFind?.text =  sb.toString()
                }
            } finally {
                urlConnection?.disconnect()
            }


        }.start()






        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
        initRepo()
        binding.navView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                //   R.id.navigation_list_movie-> supportFragmentManager.popBackStack()
                //Toast.makeText(this,"Список", Toast.LENGTH_SHORT).show()
                R.id.navigation_favorite_movie -> supportFragmentManager.popBackStack()
                //Toast.makeText(this,"Избранное", Toast.LENGTH_SHORT).show()
                R.id.navigation_ratings_movie -> supportFragmentManager.popBackStack()
                //Toast.makeText(this,"Рейтинг", Toast.LENGTH_SHORT).show()

            }

            true
        })
        initRecyclerView()
    }

    fun initRepo() {
        (applicationContext as App).fillRepoCrazzy()
        (applicationContext as App).fillRepoFant()
    }

    fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_movie)
        initRecyclerViewGroup( recyclerView, adapter, (applicationContext as App).moviesRepo)
        recyclerViewTwo = findViewById<RecyclerView>(R.id.recycler_view_movie_2)
        initRecyclerViewGroup(recyclerViewTwo, adapterTwo,(applicationContext as App).moviesRepoTwo
        )
    }

    private fun initRecyclerViewGroup(
        recyclerView: RecyclerView?,
        adapter: MoviesAdapter,
        moviesRepo: MovieRepository
    ) {

        var linearLayout: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerView?.layoutManager = linearLayout
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
        adapter.setDataBase(moviesRepo.getMovie())
        adapter.setOnItemClickListener(object : MoviesAdapter.onItemClickListener {
            override fun onItemClick(item: Movie) {
                openMovieScreen(item)
                Toast.makeText(this@MainActivity, item.id.toString(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun openMovieScreen(movie: Movie?) {
        loadFragment(OneMovieFragment(), movie!!)
    }
    fun Snackbar.setTextString(int: Int):String{
        return  resources.getString(int)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var  snack : Snackbar = Snackbar.make(binding.snackbarView!!,"",LENGTH_SHORT)
        when (item.itemId) {
            R.id.setting -> Snackbar.make(binding.snackbarView!!,snack.setTextString(R.string.settings),LENGTH_SHORT).show()

                //Toast.makeText(this, "Настройки", Toast.LENGTH_LONG).show()
            R.id.exit -> Toast.makeText(this, "Выход", Toast.LENGTH_LONG).show()
            R.id.search_movie -> Toast.makeText(this, "поиск", Toast.LENGTH_LONG).show()
            android.R.id.home -> supportFragmentManager.popBackStack()
            //  Toast.makeText(this,"Назад",Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction(  )
        transaction.addToBackStack(null)
        transaction.replace(R.id.one_movie_fragment, fragment)
        transaction.commit()

    }

    private fun loadFragment(fragment: Fragment, movie: Movie) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(
            R.id.one_movie_fragment,
            (fragment as OneMovieFragment).newInstance(movie)
        )
        transaction.commit()

    }

    override fun openListMovie() {
        initRecyclerView()
        //  Toast.makeText(this,"Н++",Toast.LENGTH_LONG).show()
    }

    override fun openOneMovie() {

    }

}