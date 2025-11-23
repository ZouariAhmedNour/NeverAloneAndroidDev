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

    }
}