package com.rajan.graphqlmvvm.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajan.graphqlmvvm.data.common.RickAndMortyResult
import com.rajan.graphqlmvvm.domain.models.CharactersModel
import com.rajan.graphqlmvvm.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase)
    :ViewModel() {

        private val _resultListCharacters =  MutableLiveData<RickAndMortyResult<CharactersModel>>()
    val resultListCharacter : LiveData<RickAndMortyResult<CharactersModel>> = _resultListCharacters

    fun getListCharacters(page: Int){
        viewModelScope.launch {
            getCharactersUseCase(page).collect{
                _resultListCharacters.postValue(it)
            }
        }
    }
}