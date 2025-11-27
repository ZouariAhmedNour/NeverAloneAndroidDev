package epi.projet.neveralone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import epi.projet.neveralone.databinding.ActivityVideosBinding

class VideosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)

        BottomNavHelper.setupNavigation(this, binding.bottomNav)
        binding.bottomNav.selectedItemId = R.id.nav_video

    }
}