package epi.projet.neveralone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import epi.projet.neveralone.databinding.ItemVideoBinding

class VideoAdapter(
    private val items: List<Video>,
    private val onOpen: (String) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(val b: ItemVideoBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = items[position]
        val thumbnailUrl = "https://img.youtube.com/vi/${v.id}/hqdefault.jpg"
        Glide.with(holder.b.imgThumbnail.context)
            .load(thumbnailUrl)
            .into(holder.b.imgThumbnail)

        holder.b.txtTitle.text = v.title
        holder.b.txtDesc.text = v.description

        holder.b.root.setOnClickListener {
            onOpen(v.url)
        }
    }

    override fun getItemCount(): Int = items.size
}