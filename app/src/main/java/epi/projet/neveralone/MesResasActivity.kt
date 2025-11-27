package epi.projet.neveralone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import epi.projet.neveralone.databinding.ActivityMesResasBinding

class MesResasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMesResasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMesResasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMesResas)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarMesResas.setNavigationOnClickListener { finish() }

        binding.recyclerResas.layoutManager = LinearLayoutManager(this)
        binding.recyclerResas.adapter = ResaAdapter(ReservationStore.reservations)

        BottomNavHelper.setupNavigation(this, binding.bottomNav)
    }
}
