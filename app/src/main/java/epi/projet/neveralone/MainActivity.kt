package epi.projet.neveralone

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ouvrir LoginActivity comme page principale
        startActivity(Intent(this, LoginActivity::class.java))

        // Fermer MainActivity pour ne pas revenir dessus
        finish()
    }
}
