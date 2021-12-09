package space.kuz.searchmoviesapp.util.mvp

import android.location.Location

fun Location.toPrintString(): String{
    return "[ $latitude , $longitude ]"
}

