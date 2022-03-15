package com.rajan.graphqlmvvm.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajan.graphqlmvvm.databinding.ItemEpisodeBinding
import com.rajan.graphqlmvvm.domain.models.EpisodeModel

class EpisodeAdapter(private val list: List<EpisodeModel>):
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>(){

        inner class ViewHolder(private val view: ItemEpisodeBinding):
                RecyclerView.ViewHolder(view.root)
        {
                    fun bindTo(episodeModel: EpisodeModel)
                    {
                        view.tvEpisode.text = episodeModel.name
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
       ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context),
       parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bindTo(list[position])
    }

    override fun getItemCount(): Int= list.size
}