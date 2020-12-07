package com.example.noforeignland_exam_pgr208.ui.places.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.noforeignland_exam_pgr208.ui.utils.ClickListener
import com.example.noforeignland_exam_pgr208.R
import com.example.noforeignland_exam_pgr208.data.model.places.Places
import kotlinx.android.synthetic.main.rows_of_place_names.view.*

class NoForeignLandAdapter(
    private var placesList: MutableList<Places>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<NoForeignLandAdapter.RecyclerViewHolder>(), Filterable {

    private var searchList: MutableList<Places> = placesList

    override fun getItemCount() = placesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rows_of_place_names, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(placesList[position])
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Places) {

            itemView.name.text = item.properties.name
            itemView.recycler_view_places.setOnClickListener {
                clickListener.onPlacesNameClick(item.properties.id)
            }

            itemView.icon.setOnClickListener {
                clickListener.onIconClick(
                    item.geometry.coordinates[1], item.geometry.coordinates[0], item.properties.name
                )
            }
        }
    }

    //Filter place names
    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val listTemp = searchList

                val filterResults = FilterResults()
                val resultList: MutableList<Places> = ArrayList()

                if (charSequence.isEmpty()) {
                    resultList.addAll(listTemp)
                } else {
                    val filterPattern = charSequence.toString().toLowerCase().trim()
                    for (item in listTemp) {
                        if (item.properties.name.toLowerCase().contains(filterPattern)) {
                            resultList.add(item)
                        }
                    }
                }

                filterResults.values = resultList;
                return filterResults
            }

            override fun publishResults(cS: CharSequence, results: FilterResults) {
                val newList: MutableList<Places>? = results.values as MutableList<Places>
                if (!newList.isNullOrEmpty()) {
                    placesList = newList
                    notifyDataSetChanged()
                }
            }
        }
    }

}
