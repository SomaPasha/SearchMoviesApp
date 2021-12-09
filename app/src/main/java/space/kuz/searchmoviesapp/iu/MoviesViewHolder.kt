package space.kuz.searchmoviesapp.iu

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.MovieClass

class MoviesViewHolder(
    itemView: ViewGroup,
    listener: MoviesAdapter.onItemClickListener?,

)
 : RecyclerView.ViewHolder(itemView) {


    private  var image: ImageView =itemView.findViewById<ImageView>(R.id.image_movie_image_view)
    private var nameTextView: TextView? =itemView.findViewById<TextView>(R.id.name_movie_text_view)
    private var yearTextView: TextView? =itemView.findViewById<TextView>(R.id.year_movie_text_view)
    private var ratingsTextView: TextView? =itemView.findViewById<TextView>(R.id.ratings_movie_text_view)
    private lateinit var movie:MovieClass
        init {
            itemView.setOnClickListener { listener?.onItemClick(movie) }
        }

    fun bind(item: MovieClass) {
        this.movie = item
        if(item.image.isNotBlank()){
            Glide.with(image.context)
                .load(item.image)
                .placeholder(R.drawable.ic_baseline_close_24)
                .error(R.drawable.ic_baseline_close_24)
                .into(image)
        } else{
            image.setImageResource(R.drawable.ic_baseline_close_24)
        }

        //image!!.setImageResource(item.image)
        nameTextView!!.text = item.name
        yearTextView!!.text = item.year
        ratingsTextView!!.text = item.rating.toString()

    }

}
