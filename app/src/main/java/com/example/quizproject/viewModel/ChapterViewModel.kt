package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.BookModel
import com.example.quizproject.dataModel.ChapterModel
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class ChapterViewModel(

    private val repository: MongoRepository

) : ViewModel() {


    var _chapterData = mutableStateOf(emptyList<ChapterModel>())

    var _chapterName = mutableStateOf("")



    init {
        viewModelScope.launch {

            repository.getChapterData().collect {
                _chapterData.value = it
                Log.d(">>>>","$it")


            }

        }
    }




    fun insertChapter() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_chapterName.value.isNotEmpty()) {

                Log.d(">>>",_chapterName.value)

                repository.insertChapter( chapterModel = ChapterModel().apply {
                    chapterName = this@ChapterViewModel._chapterName.value
                })
            }
        }
    }

    fun updateChapter( id : ObjectId ,
                       questionNo : String ,
                       questionType : String,
                       questionText : String ,
                       questionImage : String ,
                       answers : List<AnswerModel> ,
                       correctAnswer : String ,
                       solutionType : String,
                       solutionText : String,
                       solutionImage : String ) {


        viewModelScope.launch(Dispatchers.IO) {

            repository.updateChapter( chapterModel = ChapterModel().apply {
                _id = id

                Log.d("IMPL id >>>>>","$_id")
                /*CourseModel(
                   courseId,
                     courseName
                )*/
                //name = this@HomeViewModel.name.value
            } ,questionNo, questionType ,questionText ,questionImage, answers, correctAnswer, solutionType, solutionText, solutionImage )

        }
    }


}