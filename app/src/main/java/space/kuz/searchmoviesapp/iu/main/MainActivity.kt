package space.kuz.searchmoviesapp.iu.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.squareup.picasso.Picasso
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.implimentation.MovieRepositoryImplementation
import space.kuz.searchmoviesapp.iu.MoviesAdapter
import space.kuz.searchmoviesapp.iu.fragment.ListMovieFragment
import space.kuz.searchmoviesapp.iu.fragment.OneMovieFragment
import java.util.*

class MainActivity : AppCompatActivity(), ListMovieFragment.Controller,
OneMovieFragment.Controller{
 var moviesRepo:MovieRepository = MovieRepositoryImplementation()
     var recyclerView:RecyclerView?=null
     var adapter: MoviesAdapter = MoviesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item: MenuItem ->
           when(item.itemId){
         //   R.id.navigation_list_movie-> supportFragmentManager.popBackStack()
             //Toast.makeText(this,"Список", Toast.LENGTH_SHORT).show()
               R.id.navigation_favorite_movie-> supportFragmentManager.popBackStack()
               //Toast.makeText(this,"Избранное", Toast.LENGTH_SHORT).show()
               R.id.navigation_ratings_movie-> supportFragmentManager.popBackStack()
               //Toast.makeText(this,"Рейтинг", Toast.LENGTH_SHORT).show()

           }

            true
        })
        fillRepo()
        initRecyclerView()
    }
     fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_movie)
       var linearLayout: LinearLayoutManager = LinearLayoutManager(this,
        LinearLayoutManager.HORIZONTAL,false)
       recyclerView?.layoutManager = linearLayout
         recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
       adapter.setDataBase(moviesRepo.getMovie())
         adapter.setOnItemClickListener(object :MoviesAdapter.onItemClickListener{
             override fun onItemClick(item: Movie) {
                 openMovieScreen(item)
                 Toast.makeText(this@MainActivity, item.id.toString(), Toast.LENGTH_SHORT).show();
             }
         })

    }
    fun openMovieScreen(movie:Movie?) {
        loadFragment( OneMovieFragment(), movie!!)
    }
     fun fillRepo() {
      moviesRepo.createMovie(Movie(R.drawable.duna_image, "Я Фильм про1","Duna", "2020",  6.9F))
        moviesRepo.createMovie(Movie(R.drawable.dgek,"Я Фильм про2","Dgek", "2019",  6.8F))
        moviesRepo.createMovie(Movie(R.drawable.doctor, "Я Фильм про3","Doctor", "2021",  6.7F))
        moviesRepo.createMovie(Movie(R.drawable.kosmos,"Я Фильм про4","Cosmos", "2018",  6.5F))
        moviesRepo.createMovie(Movie(R.drawable.rasomaxa,"Я Фильм про5","Rasomaxa", "2010",  6.4F))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.setting-> Toast.makeText(this,"Настройки",Toast.LENGTH_LONG).show()
            R.id.exit-> Toast.makeText(this,"Выход",Toast.LENGTH_LONG).show()
            R.id.search_movie-> Toast.makeText(this,"поиск",Toast.LENGTH_LONG).show()
            android.R.id.home -> supportFragmentManager.popBackStack()
              //  Toast.makeText(this,"Назад",Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.one_movie_fragment, fragment)
        transaction.commit()

    }
    private fun loadFragment(fragment: Fragment , movie:Movie){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.one_movie_fragment,  (fragment as OneMovieFragment).newInstance(movie))
        transaction.commit()

    }

    override fun openListMovie() {
       initRecyclerView()
      //  Toast.makeText(this,"Н++",Toast.LENGTH_LONG).show()
    }

    override fun openOneMovie() {

    }

}