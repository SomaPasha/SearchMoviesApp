package space.kuz.searchmoviesapp.iu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {
    interface onItemClickListener {
        fun onItemClick(item:Movie);
    }

    var data:List<Movie> = ArrayList()
    var listener: onItemClickListener? = null;

    fun setDataBase(data:List<Movie>){
        this.data = data
        notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie,parent,false)
        return MoviesViewHolder(itemView as ViewGroup,listener)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

     fun getItem(position:Int):Movie{
        return data.get(position)
     }


    override fun getItemCount(): Int {
       return data.size
    }

    fun setOnItemClickListener( listener: onItemClickListener){this.listener= listener}

}


