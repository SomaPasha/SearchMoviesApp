package space.kuz.searchmoviesapp.iu.fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.iu.main.MainActivity

class OneMovieFragment:Fragment() {
    private lateinit var toolbar: MaterialToolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_one_movie, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
    }

    private fun initToolBar(view: View) {
        toolbar = view.findViewById(R.id.list_movie_toolbar)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_one_movie, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}