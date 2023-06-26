package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.ChapterModel
import com.example.quizproject.dataModel.CourseModel
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
}