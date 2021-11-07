package space.kuz.searchmoviesapp.iu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.implimentation.MovieRepositoryImplementation
import space.kuz.searchmoviesapp.iu.fragment.OneMovieFragment

class MainActivity : AppCompatActivity() {

    private var moviesRepo:MovieRepository = MovieRepositoryImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        fillRepo()


        //supportActionBar.hide()
    }

    private fun fillRepo() {
       moviesRepo.createMovie(Movie("dfd","Я Фильм про1","Duna1", "2020",  6.9F))
        moviesRepo.createMovie(Movie("dfd","Я Фильм про2","Duna2", "2020",  6.8F))
        moviesRepo.createMovie(Movie("dfd","Я Фильм про3","Duna3", "2020",  6.7F))
        moviesRepo.createMovie(Movie("dfd","Я Фильм про4","Duna4", "2020",  6.5F))
        moviesRepo.createMovie(Movie("dfd","Я Фильм про5","Duna5", "2020",  6.4F))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.setting-> Toast.makeText(this,"Настройки",Toast.LENGTH_LONG).show()
            R.id.exit-> Toast.makeText(this,"Выход",Toast.LENGTH_LONG).show()
            R.id.search_movie->loadFragment( OneMovieFragment())
                //Toast.makeText(this,"поиск",Toast.LENGTH_LONG).show()
            android.R.id.home -> Toast.makeText(this,"Назад",Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.one_movie_fragment, fragment)
        transaction.commit()
    }

}