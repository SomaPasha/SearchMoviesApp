package space.kuz.searchmoviesapp.iu.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.iu.main.MainActivity

class ListMovieFragment : Fragment() {
    private lateinit var toolbar: MaterialToolbar
    private  var controller: Controller? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list_movie, container,false)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = if (context is Controller) {
            context
        } else {
            throw IllegalStateException("Activity must implement ListMovieFragment.Controller")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
        controller!!.openListMovie()
       // (requireActivity() as MainActivity).adapter.setDataBase(  (requireActivity() as MainActivity).moviesRepo.getMovie())
       // (requireActivity() as MainActivity).initRecyclerView()

    }

    interface Controller {
        fun openListMovie()
    }

    private fun initToolBar(view: View) {
        toolbar = view.findViewById(R.id.list_movie_toolbar)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
       // (requireActivity() as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_list_movie, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        controller = null
        super.onDestroy()
    }

}