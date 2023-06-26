package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepository
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionSetViewModel(

    private val repository: MongoRepository

) : ViewModel(){

    var _questionSetData = mutableStateOf(emptyList<QuestionSet>())

    var _questionNo = mutableStateOf("")

    var _questionType = mutableStateOf("")

    var _questionText = mutableStateOf("")

    var _questionImage = mutableStateOf("")

    var _correctAnswer = mutableStateOf("")

    var _solutionType = mutableStateOf("")

    var _solutionText = mutableStateOf("")

    var _solutionImage = mutableStateOf("")

    var _answerListData : List<AnswerModel> = listOf<AnswerModel>()



    init {
        viewModelScope.launch {

            repository.getQuestionSetData().collect {
                _questionSetData.value = it
                Log.d(">>>>","$it")

            }

        }
    }




    fun insertQuestionSet( ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_questionText.value.isNotEmpty()) {

                Log.d(">>>",_questionText.value)

                repository.insertQuestionSet( questionSet = QuestionSet().apply {
                    questionNo = this@QuestionSetViewModel._questionNo.value
                    questionType = this@QuestionSetViewModel._questionType.value
                    questionText = this@QuestionSetViewModel._questionText.value
                    questionImage =this@QuestionSetViewModel._questionImage.value

                    /*for ( item in _answerListData ){
                        answers.add(item)
                    }*/
                    solutionType = this@QuestionSetViewModel._solutionType.value
                    solutionText = this@QuestionSetViewModel._solutionText.value
                    solutionImage = this@QuestionSetViewModel._solutionImage.value
                    correctAnswerType = this@QuestionSetViewModel._correctAnswer.value
                })
            }
        }
    }


}