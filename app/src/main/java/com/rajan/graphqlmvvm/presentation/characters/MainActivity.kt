package com.rajan.graphqlmvvm.presentation.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.rajan.graphqlmvvm.R
import com.rajan.graphqlmvvm.data.common.onError
import com.rajan.graphqlmvvm.data.common.onLoading
import com.rajan.graphqlmvvm.data.common.onSuccess
import com.rajan.graphqlmvvm.databinding.ActivityMainBinding
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import com.rajan.graphqlmvvm.domain.models.SingleCharacterModel
import com.rajan.graphqlmvvm.presentation.details.DetailsActivity
import com.rajan.graphqlmvvm.utils.CHARACTER_EXTRA
import com.rajan.graphqlmvvm.utils.hide
import com.rajan.graphqlmvvm.utils.show
import com.rajan.graphqlmvvm.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel :CharactersViewModel by viewModels()
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
        viewModel.getListCharacters(1)
    }

    private fun initObserver() {
        viewModel.resultListCharacter.observe(this){
            it.onSuccess {
                binding.progress.hide()
                setListCharacter(it)
            }
            it.onError {error->
                binding.progress.hide()
                when(error.messageResource){
                    is Int -> toast(getString(error.messageResource))
                    is Error? ->{
                        error.messageResource?.let {
                            toast(it.message?:"")
                        }
                    }
                }
            }
            it.onLoading {
                binding.progress.show()
            }
        }
    }

    private fun setListCharacter(charactersModel: CharactersModel) {
        with(binding.recyclerView){
            adapter = CharactersAdapter(charactersModel.results){
                Intent(this@MainActivity,DetailsActivity::class.java).apply{
                   putExtra(CHARACTER_EXTRA,it as SingleCharacterModel)
                    startActivity(this)

                }
            }
        }
    }
}