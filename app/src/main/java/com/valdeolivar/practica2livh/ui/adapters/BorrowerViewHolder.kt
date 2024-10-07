package com.valdeolivar.practica2livh.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import com.valdeolivar.practica2livh.databinding.BorrowerElementBinding

class BorrowerViewHolder (
    private val binding: BorrowerElementBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(borrower: BorrowerDto){

            binding.tvAlias.text = borrower.alias
            binding.tvNombre.text = borrower.name + " " + borrower.surname
            binding.tvAdress.text = borrower.adressB

            //con Glide
            Glide.with(binding.root.context)
                .load(borrower.image)
                .into(binding.ivImageU)


    }

}