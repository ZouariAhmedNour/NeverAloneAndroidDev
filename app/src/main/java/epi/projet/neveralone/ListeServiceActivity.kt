package epi.projet.neveralone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import epi.projet.neveralone.databinding.ActivityListeServiceBinding

class ListeServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListeServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListeServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Liste des services
        val listeServices = listOf(
            Service(R.drawable.jardinage, "Jardinage", "Tonte, taille, entretien"),
            Service(R.drawable.outdooract, "Activités extérieures", "Promenade, sorties"),
            Service(R.drawable.painting, "Peinture", "Décoration, rénovation")
        )

        // Config RecyclerView
        binding.recyclerServices.layoutManager = LinearLayoutManager(this)
        binding.recyclerServices.adapter = ServiceAdapter(listeServices)
    }
}
