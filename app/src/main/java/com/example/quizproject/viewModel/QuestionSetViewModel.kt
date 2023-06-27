package com.example.quizproject.viewModel

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepository
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId

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

    var _uriQuestion = mutableStateOf<Uri?>(null)

    var questionSet : QuestionSet = QuestionSet()

    var questionNo =""

    var _uriSolution = mutableStateOf<Uri?>(null)



    init {
        viewModelScope.launch {

            repository.getQuestionSetData().collect {
                _questionSetData.value = it
                Log.d(">>>>","$it")
                Log.d(">>>>ViewModelQuestion","${_questionSetData.value.size}")


            }

        }
    }
fun getDataItem(id: BsonObjectId){

    for ( item in _questionSetData.value) {

        if(item._id.equals(id)) {

            questionNo = item.questionNo
            questionSet = item
            Log.d("Submit>>>>>" , "$questionNo")
            Log.d("Item>>>>" , "${item}")


        }

    }
}



    fun insertQuestionSet( answerList: List<AnswerModel>) {
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
    }


}