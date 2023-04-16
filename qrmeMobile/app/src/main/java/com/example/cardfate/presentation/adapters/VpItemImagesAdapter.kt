package com.example.cardfate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cardfate.databinding.MainVpItemCardBinding
import com.example.cardfate.domain.entity.Card
import com.squareup.picasso.Picasso

class VpItemImagesAdapter() :
    Adapter<VpItemImagesAdapter.ItemImageViewHolder>() {

    var cards: List<Card> = listOf()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    var onItemClickListener: ((Card) -> Unit)? = null

    class ItemImageViewHolder(val binding: MainVpItemCardBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageViewHolder {
        val binding = MainVpItemCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemImageViewHolder(binding)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: ItemImageViewHolder, position: Int) {
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
