package space.kuz.searchmoviesapp.iu

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import space.kuz.searchmoviesapp.R
import space.kuz.searchmoviesapp.domain.entity.Movie
import space.kuz.searchmoviesapp.iu.main.MainActivity

class MoviesViewHolder(itemView: ViewGroup, lisener: onItemClickListener?)
 : RecyclerView.ViewHolder(itemView) {


    private var image: ImageView? =itemView.findViewById<ImageView>(R.id.image_movie_image_view)
    private var nameTextView: TextView? =itemView.findViewById<TextView>(R.id.name_movie_text_view)
    private var yearTextView: TextView? =itemView.findViewById<TextView>(R.id.year_movie_text_view)
    private var ratingsTextView: TextView? =itemView.findViewById<TextView>(R.id.ratings_movie_text_view)
    private lateinit var movie:Movie
        init {
           // image = itemView.findViewById(R.id.image)
         LayoutInflater.from(itemView.context).inflate(R.layout.item_movie, itemView,false)
            itemView.setOnClickListener{
                if (lisener != null) {
                    lisener.onItemClick(movie)
                }
            }
        }


    fun bind(item: Movie) {
        this.movie = item
        image!!.setImageResource(item.image)
       //image!!.setImageURI(item.image)
      //  Picasso.with(MainActivity()).load(item.image).into(image)
            //   Picasso.with().setIndicatorsEnabled(true)
     //   Picasso.with().
      //  load(1)
            //"http://2.bp.blogspot.com/-G_BQbLpOKtM/VL0FG0zSmkI/AAAAAAAAw-g/g3JZMK0yGCA/w620/1%2B%D0%BA%D1%80%D0%B0%D1%81%D0%B8%D0%B2%D1%8B%D0")
        //    .placeholder(R.drawable.duna_image)
        //    .into(image)
        nameTextView!!.text = item.name
        yearTextView!!.text = item.year
        ratingsTextView!!.text = item.rating.toString()

    }
}
