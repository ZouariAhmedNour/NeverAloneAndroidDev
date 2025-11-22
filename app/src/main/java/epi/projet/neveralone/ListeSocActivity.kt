package epi.projet.neveralone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import epi.projet.neveralone.databinding.ActivityListeSocBinding

class ListeSocActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeSocBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListeSocBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar (flèche retour)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Liste des sociétés"
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Liste statique
        val societes = listOf(
            Societe("Société 1", "Description de la société 1", R.drawable.soc1),
            Societe("Société 2", "Description de la société 2", R.drawable.soc2),
            Societe("Société 3", "Description de la société 3", R.drawable.soc3),
            Societe("Société 4", "Description de la société 4", R.drawable.soc4)
        )

        // RecyclerView
        binding.recyclerViewSoc.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSoc.adapter = SocieteAdapter(societes)
    }
}
