package space.kuz.searchmoviesapp.iu.fragment

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.iu.main.MainActivity

class OneMovieFragment:Fragment() {
    private lateinit var toolbar: MaterialToolbar
    private  var controller: OneMovieFragment.Controller? = null
    private lateinit  var  nameOneMovieTextView : TextView
    private lateinit  var  descriptionOneMovieTextView : TextView
    private lateinit  var  ratingsOneMovieTextView : TextView
    private lateinit  var  imageOneMovie : ImageView

    private lateinit var movie:Movie
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_one_movie, container,false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = if (context is OneMovieFragment.Controller) {
            context
        } else {
            throw IllegalStateException("Activity must implement EditMovieFragment.Controller")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
        initView(view)
        val bundle = arguments
        bundle?.let { putAndSetView(it) }
        controller!!.openOneMovie()
    }

    private fun putAndSetView(bundle: Bundle) {
        movie = bundle.getParcelable(ARG_MOVIE_ONE)!!
        nameOneMovieTextView.text = movie.name
        descriptionOneMovieTextView.text = movie.description
        ratingsOneMovieTextView.text = movie.rating.toString()
        imageOneMovie.setImageResource(movie.image)
    }
    private fun initView(view: View) {
        nameOneMovieTextView = view.findViewById(R.id.one_movie_name_text_view)
        descriptionOneMovieTextView = view.findViewById(R.id.one_movie_description_text_view)
        ratingsOneMovieTextView = view.findViewById(R.id.one_movie_ratings_text_view)
        imageOneMovie = view.findViewById(R.id.one_movie_image)
    }
    interface Controller {
        fun openOneMovie()
    }

    private fun initToolBar(view: View) {
        toolbar = view.findViewById(R.id.one_movie_toolbar)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_one_movie, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        controller = null
        super.onDestroy()
    }
    companion  object {
        private const val ARG_MOVIE_ONE = "MOVIE_ONE"
    }
    fun newInstance(movie: Movie): Fragment {
        val fragment = OneMovieFragment()
        val bundle = Bundle()
        bundle.putParcelable(ARG_MOVIE_ONE, movie)
        fragment.arguments = bundle
        return fragment
    }



}