package com.example.cardfate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.cardfate.databinding.FavoriteRvItemBinding
import com.example.cardfate.databinding.MainRvItemCardNotSelectedBinding
import com.example.cardfate.databinding.MainRvItemCardSelectedBinding
import com.example.cardfate.databinding.MainVpItemCardBinding
import com.example.cardfate.domain.entity.Card
import com.squareup.picasso.Picasso

class RvItemsFavoriteAdapter() :
    Adapter<RvItemsFavoriteAdapter.ItemFavoriteViewHolder>() {

    var cards: List<Card> = listOf()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    var onItemClickListener: ((Card) -> Unit)? = null

    class ItemFavoriteViewHolder(val binding: FavoriteRvItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFavoriteViewHolder {
        val binding = FavoriteRvItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemFavoriteViewHolder(binding)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: ItemFavoriteViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(cards[position])
        }
        with(holder.binding) {
            with(cards[position]) {
                tvUserName.text = fullName
                tvProfession.text = skills
                Picasso
                    .get()
                    .load(this.imageUrl)
                    .into(ivUserImage)
            }
        }
    }
}
