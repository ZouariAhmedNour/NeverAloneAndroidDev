package epi.projet.neveralone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epi.projet.neveralone.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clique sur modifier
        binding.btnModifierProfile.setOnClickListener {
            val nom = binding.etNomProfile.text.toString()
            val prenom = binding.etPrenomProfile.text.toString()
            val pwd = binding.etPasswordProfile.text.toString()
            val confirm = binding.etConfirmPasswordProfile.text.toString()

            if (pwd != confirm) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Profil modifié avec succès", Toast.LENGTH_SHORT).show()
        }

        BottomNavHelper.setupNavigation(this, binding.bottomNav)
        binding.bottomNav.selectedItemId = R.id.nav_profile


        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }
}
