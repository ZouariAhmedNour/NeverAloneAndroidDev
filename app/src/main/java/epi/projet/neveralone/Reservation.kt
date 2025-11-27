package epi.projet.neveralone

import android.net.Uri

data class Reservation(
    val serviceName: String,
    val imageRes: Int,
    val date: String,
    val time: String,
    val address: String,
    val attachedPhotos: List<Uri> = emptyList(),
    var status: String = "En attente" // valeur initiale
)
