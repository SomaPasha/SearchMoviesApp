package space.kuz.searchmoviesapp.iu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.MovieClass

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    interface onItemClickListener {
        fun onItemClick(item: MovieClass);
    }

    var data: List<MovieClass> = ArrayList()
    var listener: onItemClickListener? = null;

    fun setDataBase(data: List<MovieClass>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
             var itemView = LayoutInflater.from(parent.context)
                 .inflate(R.layout.item_movie, parent, false) as ViewGroup
        return MoviesViewHolder(itemView!!, listener)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       holder.bind(getItem(position))
    }


    fun getItem(position: Int): MovieClass {
        return data.get(position)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        this.listener = listener
    }

}


