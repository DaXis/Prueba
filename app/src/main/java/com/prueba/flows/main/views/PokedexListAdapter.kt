package com.prueba.flows.main.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.prueba.databinding.ItemPokedexBinding
import com.prueba.db.PokemonObj
import com.prueba.utils.TypesConstants
import com.prueba.utils.UtilsExtensions.orZero

class PokedexListAdapter(private val listItemClickListener: ListItemClickListener) : RecyclerView.Adapter<PokedexListAdapter.ViewHolder>() {

    private lateinit var binding: ItemPokedexBinding
    val differ = AsyncListDiffer(this, differCallback)

    interface ListItemClickListener {
        fun onItemClick(item: PokemonObj, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = binding.textViewName
        val number = binding.textViewNumber
        val sprite = binding.cardViewAvatar
        val typeOne = binding.imageViewTypeOne
        val typeTwo = binding.imageViewTypeTwo
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemPokedexBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokedexListAdapter.ViewHolder, position: Int) {
        val pokemon = differ.currentList[position]
        holder.name.text = pokemon.name.toUpperCase()
        holder.number.text = "#${pokemon.id}"
        holder.sprite.loadFromUrl(pokemon.sprite)

        holder.typeOne.setImageResource(TypesConstants().typesMap[pokemon.typeOne].orZero())
        TypesConstants().typesMap[pokemon.typeTwo]?.let {
            holder.typeTwo.setImageResource(it)
        } ?: run {
            holder.typeTwo.visibility = View.GONE
        }

        holder.root.setOnClickListener {
            listItemClickListener.onItemClick(pokemon, position)
        }
    }

    override fun getItemCount() = differ.currentList.size

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<PokemonObj>() {
            override fun areItemsTheSame(oldItem: PokemonObj, newItem: PokemonObj): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PokemonObj, newItem: PokemonObj): Boolean {
                return oldItem.id == newItem.id && oldItem.order == newItem.order
            }
        }
    }
}