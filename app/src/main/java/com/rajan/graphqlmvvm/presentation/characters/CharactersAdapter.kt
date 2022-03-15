package com.rajan.graphqlmvvm.presentation.characters

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rajan.graphqlmvvm.databinding.ItemCharacterBinding
import com.rajan.graphqlmvvm.domain.models.SingleCharacterModel

class CharactersAdapter(private val list: List<SingleCharacterModel>,
    private val clickAction: (SingleCharacterModel) -> Unit) :
RecyclerView.Adapter<CharactersAdapter.ViewHolder>(){

    inner class ViewHolder(private val view:ItemCharacterBinding):RecyclerView.ViewHolder(view.root)
    {
        init {
            view.root.setOnClickListener { clickAction(list[adapterPosition]) }
        }

        fun bindTo(singleCharacterModel: SingleCharacterModel){
            with(view){
                singleCharacterModel.apply {
                    tvCharacterName.text = name
                    imgCharacter.load(image){
                        crossfade(true)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
       ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindTo(list[position])
    }

    override fun getItemCount(): Int = list.size
}

