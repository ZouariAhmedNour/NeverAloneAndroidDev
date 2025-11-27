package epi.projet.neveralone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import epi.projet.neveralone.databinding.ItemResaBinding

class ResaAdapter(private val list: List<Reservation>) :
    RecyclerView.Adapter<ResaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemResaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemResaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val r = list[position]
        holder.binding.imgResa.setImageResource(r.imageRes)
        holder.binding.txtResaService.text = r.serviceName
        holder.binding.txtResaDateTime.text = "${r.date} • ${r.time}"
        holder.binding.txtResaAddress.text = r.address
        holder.binding.txtResaStatus.text = r.status

        // Colorer le statut "En attente" en orange, sinon vert si Confirmé, rouge si Annulé (exemple)
        val status = r.status
        val color = when (status) {
            "En attente" -> android.graphics.Color.parseColor("#FFA500") // orange
            "Confirmée" -> android.graphics.Color.parseColor("#4CAF50")
            "Annulée" -> android.graphics.Color.parseColor("#F44336")
            else -> android.graphics.Color.DKGRAY
        }
        holder.binding.txtResaStatus.setTextColor(color)
    }

    override fun getItemCount() = list.size
}
