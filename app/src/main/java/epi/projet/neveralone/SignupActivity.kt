package epi.projet.neveralone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epi.projet.neveralone.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    // états pour éviter de lancer l'animation inutilement
    private var lengthOk = false
    private var digitOk = false
    private var letterOk = false
    private var matchOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TextWatcher pour le mot de passe
        val pwdWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pwd = s?.toString() ?: ""
                updateConstraints(pwd)
                checkMatch(pwd, binding.etConfirmPassword.text?.toString() ?: "")
            }
        }

        // TextWatcher pour confirmer mot de passe (vérifie l'égalité)
        val confirmWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pwd = binding.etPasswordSignup.text?.toString() ?: ""
                val confirm = s?.toString() ?: ""
                checkMatch(pwd, confirm)
            }
        }

        binding.etPasswordSignup.addTextChangedListener(pwdWatcher)
        binding.etConfirmPassword.addTextChangedListener(confirmWatcher)

        // Créer compte : n'autorise que si toutes les contraintes sont valides
        binding.btnCreateAccount.setOnClickListener {
            val pwd = binding.etPasswordSignup.text?.toString() ?: ""
            val confirm = binding.etConfirmPassword.text?.toString() ?: ""

            if (lengthOk && digitOk && letterOk && matchOk) {
                Toast.makeText(
                    this,
                    "Votre compte a été créé avec succès. Veuillez vous connecter.",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Mot de passe invalide ou non confirmé. Vérifiez les contraintes.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvAlreadyHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun updateConstraints(pwd: String) {
        // longueur >= 5
        val newLengthOk = pwd.length >= 5
        if (newLengthOk != lengthOk) {
            lengthOk = newLengthOk
            animateConstraintChange(binding.tvPwdLength, lengthOk, "Minimum 5 caractères")
        }

        // au moins un chiffre
        val newDigitOk = pwd.any { it.isDigit() }
        if (newDigitOk != digitOk) {
            digitOk = newDigitOk
            animateConstraintChange(binding.tvPwdDigit, digitOk, "Au moins un chiffre")
        }

        // au moins une lettre
        val newLetterOk = pwd.any { it.isLetter() }
        if (newLetterOk != letterOk) {
            letterOk = newLetterOk
            animateConstraintChange(binding.tvPwdLetter, letterOk, "Au moins une lettre")
        }
    }

    private fun checkMatch(pwd: String, confirm: String) {
        val newMatchOk = pwd.isNotEmpty() && pwd == confirm
        if (newMatchOk != matchOk) {
            matchOk = newMatchOk
            if (matchOk) {
                binding.tvPwdMatch.text = "✓ Les mots de passe correspondent"
                binding.tvPwdMatch.setTextColor(Color.parseColor("#2E7D32")) // vert
                animateView(binding.tvPwdMatch)
            } else {
                // si confirm vide, vide le texte sinon indiquer mismatch
                binding.tvPwdMatch.text = if (confirm.isEmpty()) "" else "❌ Les mots de passe ne correspondent pas"
                binding.tvPwdMatch.setTextColor(Color.parseColor("#808080"))
                // animation légère aussi si on passe à non-match
                animateView(binding.tvPwdMatch)
            }
        }
    }

    // change texte (préfixe ✓/❌) et couleur, et lance une petite animation scale
    private fun animateConstraintChange(tv: android.widget.TextView, ok: Boolean, label: String) {
        if (ok) {
            tv.text = "✓ $label"
            tv.setTextColor(Color.parseColor("#2E7D32")) // vert
        } else {
            tv.text = "❌ $label"
            tv.setTextColor(Color.parseColor("#808080")) // gris
        }
        animateView(tv)
    }

    private fun animateView(v: android.view.View) {
        v.scaleX = 0.95f
        v.scaleY = 0.95f
        v.alpha = 0.85f
        v.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(220)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }
}
