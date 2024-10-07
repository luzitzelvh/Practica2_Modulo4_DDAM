package com.valdeolivar.practica2livh.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import com.valdeolivar.practica2livh.databinding.BorrowerElementBinding

class BorrowersAdapter(
    private val borrowers: MutableList<BorrowerDto>,
    private val onBorrowerClicked: (BorrowerDto) -> Unit
): RecyclerView.Adapter<BorrowerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowerViewHolder {
        val binding = BorrowerElementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BorrowerViewHolder(binding)
    }

    override fun getItemCount(): Int = borrowers.size

    override fun onBindViewHolder(holder: BorrowerViewHolder, position: Int) {

        val borrower = borrowers[position]

        holder.bind(borrower)

        holder.itemView.setOnClickListener{
            //Para los clicks en los juegos
            onBorrowerClicked(borrower)
        }

    }


}