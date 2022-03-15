package com.rajan.graphqlmvvm.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rajan.graphqlmvvm.data.common.onError
import com.rajan.graphqlmvvm.data.common.onSuccess
import com.rajan.graphqlmvvm.data.datasource.remote.RemoteDataSourceImplTest
import com.rajan.graphqlmvvm.data.repository.AppRepositoryImplTest
import com.rajan.graphqlmvvm.domain.usecases.GetCharactersUseCase
import com.rajan.graphqlmvvm.helpers.MainCoroutineRule
import com.rajan.graphqlmvvm.helpers.runBlockingTest
import com.rajan.graphqlmvvm.presentation.characters.CharactersViewModel
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchCharactersViewModelTests {

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel:CharactersViewModel

    @Before
    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)
        viewModel =  CharactersViewModel(
            GetCharactersUseCase(AppRepositoryImplTest(RemoteDataSourceImplTest()))
        )
    }

    @After
    fun tearDown() {
//        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
//        mainThreadSurrogate.close()


    }


    @ExperimentalCoroutinesApi
    @Test
    fun `get list characters , return success` () = mainCoroutineRule.runBlockingTest {

//        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            // ...

            viewModel.getListCharacters(1)

            viewModel.resultListCharacter.value?.onSuccess { result->
                assertThat(result.info).isNotNull()
                assertThat(result.info.next).isEqualTo(2)
                assertThat(result.results).isNotNull()
                assertThat(result.results[0].name).matches("Rick Sanchez")
                assertThat(result.results[0].episode.size).isAtLeast(5)
            }?.onError {
                assertThat(it).isNull()
            }
//        }

    }
}