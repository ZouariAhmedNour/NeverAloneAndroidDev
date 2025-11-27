package epi.projet.neveralone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import epi.projet.neveralone.databinding.ActivityClientHomeBinding

class ClientHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.btnMoreProviders.setOnClickListener {
            val intent = Intent(this, ListeSocActivity::class.java)
            startActivity(intent)
        }

        binding.btnEvaluateSoc1.setOnClickListener {
            val intent = Intent(this, RateProviderActivity::class.java)
            intent.putExtra("nom_societe", "Société Jardin et Co")
            startActivity(intent)
        }

        binding.btnEvaluateSoc2.setOnClickListener {
            val intent = Intent(this, RateProviderActivity::class.java)
            intent.putExtra("nom_societe", "Société PeinturePro")
            startActivity(intent)
        }

        binding.btnMoreServices.setOnClickListener {
            val intent = Intent(this, ListeServiceActivity::class.java)
            startActivity(intent)
        }

        binding.btnReserveJardinage.setOnClickListener {
            val intent = Intent(this, ReservationsActivity::class.java)
            intent.putExtra("serviceName", "Jardinage")
            intent.putExtra("imageRes", R.drawable.jardinage)
            startActivity(intent)
        }

// Outdoor
        binding.btnReserveOutdoor.setOnClickListener {
            val intent = Intent(this, ReservationsActivity::class.java)
            intent.putExtra("serviceName", "Activités extérieures")
            intent.putExtra("imageRes", R.drawable.outdooract)
            startActivity(intent)
        }

// Painting
        binding.btnReservePainting.setOnClickListener {
            val intent = Intent(this, ReservationsActivity::class.java)
            intent.putExtra("serviceName", "Peinture et décoration")
            intent.putExtra("imageRes", R.drawable.painting)
            startActivity(intent)
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_resa -> {
                    startActivity(Intent(this, MesResasActivity::class.java))
                    true
                }
                else -> false
            }
        }

        BottomNavHelper.setupNavigation(this, binding.bottomNav)
        binding.bottomNav.selectedItemId = R.id.nav_home


    }
}