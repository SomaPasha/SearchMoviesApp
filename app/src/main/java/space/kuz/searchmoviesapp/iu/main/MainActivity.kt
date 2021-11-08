package space.kuz.searchmoviesapp.iu.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.implimentation.MovieRepositoryImplementation
import space.kuz.searchmoviesapp.iu.MoviesAdapter
import space.kuz.searchmoviesapp.iu.fragment.OneMovieFragment

class MainActivity : AppCompatActivity() {
    //private val moviesRepo: MovieRepository = MovieRepositoryImplementation()
 var moviesRepo:MovieRepository = MovieRepositoryImplementation()
    private lateinit var recyclerView:RecyclerView
     var adapter: MoviesAdapter = MoviesAdapter()
  //  private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var imageView :ImageView= findViewById<ImageView>(R.id.image_ex)
     //   imageView.setImageResource(R.drawable.duna_image)
       /* Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).
        load(1)
            //"http://2.bp.blogspot.com/-G_BQbLpOKtM/VL0FG0zSmkI/AAAAAAAAw-g/g3JZMK0yGCA/w620/1%2B%D0%BA%D1%80%D0%B0%D1%81%D0%B8%D0%B2%D1%8B%D0")
            .placeholder(R.drawable.duna_image)
            .into(imageView) */
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        fillRepo()
        initRecyclerView()

        //supportActionBar.hide()
    }
     fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_movie) as RecyclerView
       var linearLayout: LinearLayoutManager = LinearLayoutManager(this,
        LinearLayoutManager.HORIZONTAL,false)
       recyclerView.layoutManager = linearLayout
        recyclerView.adapter = adapter
       adapter.setDataBase(moviesRepo.getMovie())
       adapter.setOnItemClickListener ( this::onItemClick )

    }
    fun onItemClick(item: Movie) {
        Toast.makeText(this, "Ура", Toast.LENGTH_SHORT).show();
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