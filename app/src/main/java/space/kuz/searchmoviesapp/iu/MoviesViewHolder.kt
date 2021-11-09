package space.kuz.searchmoviesapp.iu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie

class MoviesViewHolder(
    itemView: ViewGroup,
    listener: MoviesAdapter.onItemClickListener?,

)
 : RecyclerView.ViewHolder(itemView) {


    private var image: ImageView? =itemView.findViewById<ImageView>(R.id.image_movie_image_view)
    private var nameTextView: TextView? =itemView.findViewById<TextView>(R.id.name_movie_text_view)
    private var yearTextView: TextView? =itemView.findViewById<TextView>(R.id.year_movie_text_view)
    private var ratingsTextView: TextView? =itemView.findViewById<TextView>(R.id.ratings_movie_text_view)
    private var image2: ImageView? =itemView.findViewById<ImageView>(R.id.image_movie_image_view_2)
    private var nameTextView2: TextView? =itemView.findViewById<TextView>(R.id.name_movie_text_view_2)
    private var yearTextView2: TextView? =itemView.findViewById<TextView>(R.id.year_movie_text_view_2)
    private var ratingsTextView2: TextView? =itemView.findViewById<TextView>(R.id.ratings_movie_text_view_2)
    private lateinit var movie:Movie
        init {
            itemView.setOnClickListener { listener?.onItemClick(movie) }
        }


    fun bindType1(item: Movie) {
        this.movie = item
        image!!.setImageResource(item.image)
        nameTextView!!.text = item.name
        yearTextView!!.text = item.year
        ratingsTextView!!.text = item.rating.toString()

    }
    fun bindType2(item: Movie) {
        this.movie = item
        image2!!.setImageResource(item.image)
        nameTextView2!!.text = item.name
        yearTextView2!!.text = item.year
        ratingsTextView2!!.text = item.rating.toString()

    }
}
