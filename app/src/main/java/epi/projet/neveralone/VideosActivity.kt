package epi.projet.neveralone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import epi.projet.neveralone.databinding.ActivityVideosBinding

class VideosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // -----------------------------
        //   CONFIGURATION DE L’APP BAR
        // -----------------------------
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tutoriels"   // Si tu veux forcer le titre
        binding.topAppBar.setNavigationOnClickListener { finish() }


        // Bottom nav helper
        BottomNavHelper.setupNavigation(this, binding.bottomNav)
        binding.bottomNav.selectedItemId = R.id.nav_video

        // RecyclerView
        binding.recyclerVideos.layoutManager = LinearLayoutManager(this)

        // --- Liste statique de vidéos : modifie/ajoute à ta guise ---
        val videos = listOf(
            Video(
                id = "Q0c1H1xy3eM",
                title = "Procédure d'inscription des étudiants",
                description = "Procédure d'inscription des étudiants.",
                url = "https://www.youtube.com/watch?v=Q0c1H1xy3eM"
            ),
            Video(
                id = "GjN3hlFDN9U",
                title = "SMART TRAVELLER - l'application mobile de la douane Tunisienne",
                description = "Pour tout savoir sur la réglementation, les procédures et les formalités douanières à l’entrée, au séjour et au départ de la Tunisie.",
                url = "https://www.youtube.com/watch?v=GjN3hlFDN9U"
            ),
            Video(
                id = "XV11nXMsP0A",
                title = "Inscription sur le site de l'Agence Tunisienne de Coopération Technique ATCT",
                description = "Procédure d'inscription sur le site de l'Agence Tunisienne de Coopération Technique ATCT.",
                url = "https://www.youtube.com/watch?v=XV11nXMsP0A"
            )
            // ajoute autant que tu veux...
        )
        // ----------------------------------------------------------------

        binding.recyclerVideos.adapter = VideoAdapter(videos) { url ->
            openYouTubeUrl(url)
        }
    }

    private fun openYouTubeUrl(url: String) {
        // essaie d'extraire l'ID youtube (v= ou youtu.be) et ouvrir dans app youtube d'abord
        val videoId = extractYoutubeId(url)
        if (!videoId.isNullOrBlank()) {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
            try {
                startActivity(appIntent)
            } catch (e: Exception) {
                startActivity(webIntent)
            }
        } else {
            // si on ne trouve pas l'id, ouvre l'url brute
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    // extraction simple d'un id youtube depuis plusieurs formats d'URL
    private fun extractYoutubeId(url: String): String? {
        // patterns pour v=VIDEOID et youtu.be/VIDEOID
        val regex1 = Regex("[?&]v=([A-Za-z0-9_-]{6,})")
        val regex2 = Regex("youtu\\.be/([A-Za-z0-9_-]{6,})")
        val r1 = regex1.find(url)
        if (r1 != null && r1.groups.size > 1) return r1.groupValues[1]
        val r2 = regex2.find(url)
        if (r2 != null && r2.groups.size > 1) return r2.groupValues[1]
        return null


    }


}
