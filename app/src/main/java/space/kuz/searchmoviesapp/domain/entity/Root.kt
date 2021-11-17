package space.kuz.searchmoviesapp.domain.entity

class Root {
    data class Root(
        val page:Long,
        val results:List<MovieRepo>,
        val total_pages:Long,
        val total_results:Long
    )
}