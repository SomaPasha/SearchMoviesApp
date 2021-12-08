package space.kuz.searchmoviesapp.iu.main

import android.content.*
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.databinding.ActivityMainBinding
import space.kuz.searchmoviesapp.domain.entity.MovieClass
import space.kuz.searchmoviesapp.domain.repo.MovieRepository
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepo
import space.kuz.searchmoviesapp.domain.repo.TheMovieRepoRoom
import space.kuz.searchmoviesapp.iu.MoviesAdapter
import space.kuz.searchmoviesapp.iu.fragment.ListMovieFragment
import space.kuz.searchmoviesapp.iu.fragment.OneMovieFragment
import space.kuz.searchmoviesapp.util.mvp.ExampleBroadcastReceiver
import space.kuz.searchmoviesapp.util.mvp.MyService
import java.util.*
    public val  EVENT ="Event"
object MyAnalytics{
    fun logEvent(context: Context,event:String){
        val intent =  Intent(context, MyService::class.java)
        intent.putExtra(EVENT, event)
        context.startService(intent)
    }
}
class MainActivity  :  AppCompatActivity(), ListMovieFragment.Controller,
    OneMovieFragment.Controller  {
   // private  val movieRepoRoom : TheMovieRepoRoom by lazy { app.movieRepoRoom }


    private  val  theMovieRepo: TheMovieRepo  by lazy { app.theMovieRepo }
    lateinit var  binding: ActivityMainBinding
    var recyclerView: RecyclerView? = null
    var recyclerViewTwo: RecyclerView? = null
    var adapter: MoviesAdapter = MoviesAdapter()
    var adapterTwo: MoviesAdapter = MoviesAdapter()
    val exampleBroadcastReceiver: ExampleBroadcastReceiver = ExampleBroadcastReceiver()
    val connection =  object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
          Log.d("@@@", "onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("@@@", "onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgress(true)
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
        initRepo()

        binding.navView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                //   R.id.navigation_list_movie-> supportFragmentManager.popBackStack()
                //Toast.makeText(this,"Список", Toast.LENGTH_SHORT).show()
                R.id.navigation_favorite_movie ->{
                    supportFragmentManager.popBackStack()
                //Toast.makeText(this,"Избранное", Toast.LENGTH_SHORT).show()
                    }
                R.id.navigation_ratings_movie -> {
                    supportFragmentManager.popBackStack()
             // Toast.makeText(this,"Рейтинг", Toast.LENGTH_SHORT).show()


                }

            }

            true
        })
        initRecyclerView()

        MyAnalytics.logEvent(this, "OnCreateMainActivity")

    }

    override fun onStart() {
        super.onStart()
        val filter: IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(exampleBroadcastReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        MyAnalytics.logEvent(this, "OnStopMainActivity")
        unregisterReceiver(exampleBroadcastReceiver)
    }
    private  fun showProgress(show:Boolean) {
        binding.progressBar?.isVisible = show
        recyclerView?.isVisible = !show
    }


    fun initRepo() {

            theMovieRepo.getReposForUserAsync {
             //   val repos: Call<List<MovieClass>> = theMovieRepo. .api.listRepos("octocat")
                it.forEach {
                    (applicationContext as App).moviesRepo.createMovie(
                        MovieClass(
                            "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces" +
                            it.image,
                            it.name, it.description, it.year.substring(0, 4), it.rating, 40.0  ,40.0
                        )
                        )
                }

                runOnUiThread {
                    initRecyclerView()
               //     Thread.sleep(3000)
                    if((applicationContext as App).moviesRepo.getMovie().isEmpty()){
                        Snackbar.make(binding.snackbarView!!,"Check correct connect internet",LENGTH_SHORT).show()
                    }
                    showProgress(false)
                }

        }


      //  (applicationContext as App).fillRepoCrazzy()
      //  (applicationContext as App).fillRepoFant()
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
            override fun onItemClick(item: MovieClass) {
                openMovieScreen(item)
                Toast.makeText(this@MainActivity, item.id.toString(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun openMovieScreen(movie: MovieClass?) {
        MyAnalytics.logEvent(this, "Open ${movie?.name}")
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
            R.id.exit -> {
            //    val intent = Intent(this, MyService::class.java)
            //    startService(intent)
                Toast.makeText(this, "Выход", Toast.LENGTH_LONG).show()

                bindService(Intent(this, MyService::class.java) ,connection, BIND_AUTO_CREATE)
            }
            R.id.search_movie ->
            {
                Toast.makeText(this, "поиск", Toast.LENGTH_LONG).show()
                unbindService(connection)
            }

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

    private fun loadFragment(fragment: Fragment, movie: MovieClass) {
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