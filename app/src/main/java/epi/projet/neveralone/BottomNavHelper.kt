package epi.projet.neveralone

import android.app.Activity
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

object BottomNavHelper {

    fun setupNavigation(activity: Activity, bottomNav: BottomNavigationView) {

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.ic_resa -> {
                    if (activity !is MesResasActivity) {
                        activity.startActivity(Intent(activity, MesResasActivity::class.java))
                    }
                    true
                }

                R.id.nav_home -> {
                    if (activity !is ClientHomeActivity) {
                        activity.startActivity(Intent(activity, ClientHomeActivity::class.java))
                    }
                    true
                }

                R.id.nav_video -> {
                    if (activity !is VideosActivity) {
                        activity.startActivity(Intent(activity, VideosActivity::class.java))
                    }
                    true
                }

                R.id.nav_profile -> {
                    if (activity !is ProfileActivity) {
                        activity.startActivity(Intent(activity, ProfileActivity::class.java))
                    }
                    true
                }

                else -> false
            }
        }
    }
}
