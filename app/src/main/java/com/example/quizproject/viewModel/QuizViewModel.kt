package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.launch

class QuizViewModel(
    private val repository: MongoRepository
) : ViewModel() {

    var _quizData = mutableStateOf(emptyList<QuestionSet>())
    init {
        viewModelScope.launch {

            repository.getQuestionSetData().collect {
                _quizData.value = it
                Log.d(">>>>","$it")
                Log.d(">>>>ViewModelQuestion","${_quizData.value.size}")

            }
        }
    }
}