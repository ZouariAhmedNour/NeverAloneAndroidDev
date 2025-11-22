package epi.projet.neveralone

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import epi.projet.neveralone.databinding.ItemSocieteBinding

class SocieteAdapter(private val liste: List<Societe>) :
    RecyclerView.Adapter<SocieteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSocieteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSocieteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val soc = liste[position]

        holder.binding.imgSoc.setImageResource(soc.imageRes)
        holder.binding.txtNomSoc.text = soc.nom
        holder.binding.txtDescSoc.text = soc.description

        holder.binding.btnEval.setOnClickListener {
            // 👉 OUVRIR RateProviderActivity
            val intent = Intent(holder.itemView.context, RateProviderActivity::class.java)

            // 👉 envoyer le nom de la société (optionnel)
            intent.putExtra("nom_societe", soc.nom)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = liste.size
}
