package space.kuz.searchmoviesapp.domain.entity

class Root {
    data class Root(
        var page:Long,
        var results:Array<Results>,
        var total_pages:Long,
        var total_results:Long
    )
}