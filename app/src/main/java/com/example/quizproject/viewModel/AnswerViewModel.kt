package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswerViewModel (

    private val repository: MongoRepository

) : ViewModel() {

    var _answerData = mutableStateOf(emptyList<AnswerModel>())

    var _answerType = mutableStateOf("")

    var _answerText = mutableStateOf("")

    var _answerImage = mutableStateOf("")

    var _answerOption = mutableStateOf('@')



    init {
        viewModelScope.launch {

            repository.getAnswerData().collect {
                _answerData.value = it
                Log.d(">>>>","$it")


            }

        }
    }




    fun insertAnswer() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_answerText.value.isNotEmpty()) {

                Log.d(">>>",_answerText.value)

                repository.insertAnswer( answerModel = AnswerModel().apply {
                    answerType = this@AnswerViewModel._answerType.value
                    answerText = this@AnswerViewModel._answerText.value
                    answerOption =this@AnswerViewModel._answerOption.value
                    answerImage = this@AnswerViewModel._answerImage.value
                })
            }
        }
    }
}