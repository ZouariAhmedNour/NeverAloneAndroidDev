package epi.projet.neveralone

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import epi.projet.neveralone.databinding.ActivityReservationsBinding
import java.util.*

class ReservationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationsBinding
    private val attachedUris = mutableListOf<Uri>()

    private val pickPhotosLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        if (uris != null && uris.isNotEmpty()) {
            attachedUris.clear()
            attachedUris.addAll(uris)
            binding.txtPhotosCount.text = "${attachedUris.size} photo(s) jointes"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar
        setSupportActionBar(binding.toolbarResa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarResa.setNavigationOnClickListener { finish() }

        // reçoit service + image
        val serviceName = intent.getStringExtra("serviceName") ?: "Service"
        val imageRes = intent.getIntExtra("imageRes", 0)

        binding.txtServiceName.text = serviceName
        if (imageRes != 0) binding.imgSelectedService.setImageResource(imageRes)

        // Date picker
        binding.edtDate.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, y, m, d ->
                val mm = m + 1
                binding.edtDate.setText(String.format("%04d-%02d-%02d", y, mm, d))
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Time picker
        binding.edtTime.setOnClickListener {
            val c = Calendar.getInstance()
            TimePickerDialog(this, { _, hour, min ->
                binding.edtTime.setText(String.format("%02d:%02d", hour, min))
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        // Use location button -> demande permission si besoin puis récupère la dernière position et geocodage
        binding.btnUseLocation.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001)
                return@setOnClickListener
            }
            fillAddressFromLocation()
        }

        // Joindre photos
        binding.btnAttachPhotos.setOnClickListener {
            // mime type images/*, allow multiple
            pickPhotosLauncher.launch("image/*")
        }

        // Submit
        binding.btnSubmitResa.setOnClickListener {
            val date = binding.edtDate.text.toString().trim()
            val time = binding.edtTime.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()

            if (date.isEmpty() || time.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Veuillez sélectionner la date, l'heure et l'adresse", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resa = Reservation(
                serviceName = serviceName,
                imageRes = imageRes,
                date = date,
                time = time,
                address = address,
                attachedPhotos = attachedUris.toList(),
                status = "En attente"
            )

            // Sauvegarde simple (mémoire)
            ReservationStore.reservations.add(0, resa)

            AlertDialog.Builder(this)
                .setTitle("Succès")
                .setMessage("Votre réservation est en train d'être vérifiée.")
                .setPositiveButton("OK") { _, _ ->
                    startActivity(Intent(this, ClientHomeActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    })
                    finish()
                }
                .show()
        }
    }

    // callback permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fillAddressFromLocation()
            } else {
                Toast.makeText(this, "Permission localisation refusée", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fillAddressFromLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Permission localisation non accordée", Toast.LENGTH_SHORT).show()
                return
            }

            val lm = getSystemService(LOCATION_SERVICE) as LocationManager
            val providers = lm.getProviders(true)
            var bestLoc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (bestLoc == null) {
                for (p in providers) {
                    val l = lm.getLastKnownLocation(p)
                    if (l != null) {
                        bestLoc = l
                        break
                    }
                }
            }

            if (bestLoc != null) {
                val geocoder = Geocoder(this, Locale.getDefault())
                val list = geocoder.getFromLocation(bestLoc.latitude, bestLoc.longitude, 1)

                if (!list.isNullOrEmpty()) {
                    binding.edtAddress.setText(list[0].getAddressLine(0))
                } else {
                    Toast.makeText(this, "Impossible de récupérer l'adresse", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Position introuvable", Toast.LENGTH_SHORT).show()
            }

        } catch (e: SecurityException) {
            Toast.makeText(this, "Permission refusée !", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Erreur localisation : ${e.message}", Toast.LENGTH_SHORT).show()
        }

        BottomNavHelper.setupNavigation(this, binding.bottomNav)
    }

}
