package epi.projet.neveralone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import epi.projet.neveralone.databinding.ActivityRateProviderBinding

class RateProviderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRateProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ----- Toolbar -----
        setSupportActionBar(binding.toolbarRate)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Évaluer prestataire"

        binding.toolbarRate.setNavigationOnClickListener { finish() }

        // Nom de la société (si tu veux l'afficher plus tard)
        val nomSoc = intent.getStringExtra("nom_societe")

        // ----- Bouton Envoyer -----
        binding.btnSubmit.setOnClickListener {
            val rating = binding.ratingBar.rating.toInt()
            val comment = binding.edtComment.text.toString().trim()

            if (rating == 0) {
                Toast.makeText(this, "Veuillez donner une note", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ---- Popup de succès ----
            AlertDialog.Builder(this)
                .setTitle("Succès")
                .setMessage("Votre évaluation a été envoyée avec succès.")
                .setPositiveButton("OK") { _, _ ->
                    // Redirection vers ClientHomeActivity
                    val intent = Intent(this, ClientHomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }
}
