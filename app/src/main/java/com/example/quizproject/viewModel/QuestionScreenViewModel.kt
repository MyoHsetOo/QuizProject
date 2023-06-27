package com.example.quizproject.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionScreenViewModel (

    private val repository: MongoRepository

) : ViewModel(){


    var _questionScreenData = mutableStateOf(emptyList<QuestionSet>())





    init {
        viewModelScope.launch {

            repository.getQuestionSetData().collect {
                _questionScreenData.value = it
                Log.d(">>>>","$it")
                Log.d(">>>>ViewModelQuestion","${_questionScreenData.value.size}")


            }

        }
    }


    /*fun insertQuestionSet( answerList: List<AnswerModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_questionText.value.isNotEmpty()) {

                Log.d(">>>",_questionText.value)

                repository.insertQuestionSet( questionSet = QuestionSet().apply {
                    questionNo = this@QuestionSetViewModel._questionNo.value
                    questionType = this@QuestionSetViewModel._questionType.value
                    questionText = this@QuestionSetViewModel._questionText.value
                    questionImage =this@QuestionSetViewModel._uriQuestion.value.toString()

                    for ( item in answerList ){
                        answers.add(item)
                        Log.d(">>>>answerListData", "$item")
                    }
                    solutionType = this@QuestionSetViewModel._solutionType.value
                    solutionText = this@QuestionSetViewModel._solutionText.value
                    solutionImage = this@QuestionSetViewModel._uriSolution.value.toString()
                    correctAnswerType = this@QuestionSetViewModel._correctAnswer.value
                })
            }
        }
    }*/
}