package epi.projet.neveralone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import epi.projet.neveralone.databinding.ItemServiceBinding

class ServiceAdapter(private val liste: List<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = liste[position]

        holder.binding.imgService.setImageResource(service.imageRes)
        holder.binding.txtNomService.text = service.nom
        holder.binding.txtDescService.text = service.description
    }

    override fun getItemCount() = liste.size
}
