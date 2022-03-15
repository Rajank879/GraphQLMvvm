package com.rajan.graphqlmvvm.presentation.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.rajan.graphqlmvvm.databinding.ActivityDetailsBinding
import com.rajan.graphqlmvvm.domain.models.SingleCharacterModel
import com.rajan.graphqlmvvm.utils.CHARACTER_EXTRA

class DetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailsBinding
    private val viewModel :DetailsActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtraCharacter()?.let {
            with(binding){
                tvDetailsCharacter.text = "Episodes that "+ it.name+" appeared in"
                rvEpisode.adapter = EpisodeAdapter(it.episode)
            }
        }

    }

    private fun getExtraCharacter() =
        intent?.extras?.getParcelable(CHARACTER_EXTRA) as SingleCharacterModel?
}