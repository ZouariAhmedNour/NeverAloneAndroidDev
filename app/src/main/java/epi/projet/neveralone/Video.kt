package epi.projet.neveralone

data class Video(
    val id: String,
    val title: String,
    val description: String = "",
    val url: String // ex: "https://www.youtube.com/watch?v=VIDEO_ID"
)