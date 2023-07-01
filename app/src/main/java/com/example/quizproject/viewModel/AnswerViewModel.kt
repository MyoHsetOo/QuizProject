package com.example.quizproject.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.BookModel
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class AnswerViewModel (

    private val repository: MongoRepository

) : ViewModel() {

    var _answerData = mutableStateOf(emptyList<AnswerModel>())

    var _answerType = mutableStateOf("")

    var _answerText = mutableStateOf("")

    var _answerImage = mutableStateOf("")

    var _answerOption = mutableStateOf('@')

    var _uriAnswer = mutableStateOf<Uri?>(null)

    var _isAnswerClick = mutableStateOf(false)

    init {
        viewModelScope.launch {

            repository.getAnswerData().collect {
                _answerData.value = it
                Log.d(">>>>","$it")


            }

        }
    }

    fun updateAnswer( id : ObjectId , isAnswer_Click : Boolean ) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateAnswer( answerModel = AnswerModel().apply {

                _id = id

                Log.d("IMPL id >>>>>","$_id")
                /*CourseModel(
                   courseId,
                     courseName
                )*/
                //name = this@HomeViewModel.name.value
            } , isAnswer_Click )

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
                    answerImage = this@AnswerViewModel._uriAnswer.value.toString()
                })
            }
        }
    }
}