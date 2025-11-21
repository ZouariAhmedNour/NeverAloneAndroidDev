package epi.projet.neveralone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import epi.projet.neveralone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bouton "Se connecter"
        binding.btnLogin.setOnClickListener {

            val isProvider = false

            if (isProvider) {
                startActivity(Intent(this, ProviderHomeActivity::class.java))
            } else {
                startActivity(Intent(this, ClientHomeActivity::class.java))
            }
        }

        // Texte "Pas de compte ? S'inscrire"
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
