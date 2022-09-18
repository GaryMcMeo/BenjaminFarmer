package Adapter

import DataHewan.Hewan
import Interface.InterfacePeternakan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peternakan.R
import com.example.peternakan.databinding.ActivityKartuhewanBinding

class AdapterPeternakann(val listHewan: ArrayList<Hewan>, val Interface: InterfacePeternakan) :
        RecyclerView.Adapter<AdapterPeternakann.viewHolder>() {


    class viewHolder(val itemView: View, val Interface: InterfacePeternakan) :
        RecyclerView.ViewHolder(itemView) {

        val binding = ActivityKartuhewanBinding.bind(itemView)

        fun setData(data: Hewan) {
            binding.namahewan.text = data.nama
            itemView.setOnClickListener {
                Interface.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_kartuhewan, parent, false)
        return viewHolder(view, Interface)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position])
    }

    override fun getItemCount(): Int {
        return listHewan.size
    }
}

