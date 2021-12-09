package space.kuz.searchmoviesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import space.kuz.searchmoviesapp.domain.entity.MovieClassRoom

@Database(
    entities = [MovieClassRoom::class],
    version = 1

)
abstract class MovieRoomBd : RoomDatabase() {
    abstract fun movieDao():MovieDao
}