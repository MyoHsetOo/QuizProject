package com.example.quizproject.quizz

import android.net.Uri
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
import kotlinx.coroutines.launch

class QuizChooseViewModel(

    private val repository: MongoRepository


) : ViewModel() {

    //Course
    var _courseData = mutableStateOf(emptyList<CourseModel>())

    init {
        viewModelScope.launch {

            repository.getCourseData().collect {
                _courseData.value = it
            }
        }
    }

    //Book
    var _bookData = mutableStateOf(emptyList<BookModel>())

    init {
        viewModelScope.launch {

            repository.getBookData().collect {
                _bookData.value = it
            }
        }
    }


    //Chapter
    var _chapterData = mutableStateOf(emptyList<ChapterModel>())

    init {
        viewModelScope.launch {

            repository.getChapterData().collect {
                _chapterData.value = it
           }
        }
    }


    //QuestionSet

    var _questionSetData = mutableStateOf(emptyList<QuestionSet>())

    init {
        viewModelScope.launch {

            repository.getQuestionSetData().collect {
                _questionSetData.value = it
            }
        }
    }





}