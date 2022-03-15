package com.rajan.graphqlmvvm.domain.usecases

import com.rajan.graphqlmvvm.domain.repository.AppRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(request:Int) = appRepository.getCharacters(request)
}