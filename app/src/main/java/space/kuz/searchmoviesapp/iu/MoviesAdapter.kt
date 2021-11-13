package space.kuz.searchmoviesapp.iu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {
    private val TYPE_ITEM1 = 0
    private val TYPE_ITEM2 = 1

    interface onItemClickListener {
        fun onItemClick(item: Movie);
    }

    var data: List<Movie> = ArrayList()
    var listener: onItemClickListener? = null;

    fun setDataBase(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
          var itemView:ViewGroup?=null
            when(viewType){
                TYPE_ITEM1 -> { itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false) as ViewGroup
                return MoviesViewHolder(itemView!!, listener) }
                TYPE_ITEM2 ->{
                    itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_2, parent, false) as ViewGroup
                 return MoviesViewHolder(itemView!!, listener)
                }
            }

        return MoviesViewHolder(itemView!!, listener)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val type = getItemViewType(position)
        when (type) {
            TYPE_ITEM1 -> holder.bindType1(getItem(position))
            TYPE_ITEM2 -> holder.bindType2(getItem(position))
        }
       // holder.bindType1(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {

            return TYPE_ITEM1


        //  return super.getItemViewType(position)
    }

    fun getItem(position: Int): Movie {
        return data.get(position)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        this.listener = listener
    }

}


