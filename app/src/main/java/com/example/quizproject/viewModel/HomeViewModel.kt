package com.example.quizproject.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataRepository.MongoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(

    private val repository: MongoRepository

) : ViewModel() {

    var _categoryData = mutableStateOf(emptyList<Category>())

    var _categoryName = mutableStateOf("")

    var _categoryDescription = mutableStateOf( "")

    var _categoryId = mutableStateOf("")

    init {
        viewModelScope.launch {
            repository.getCategoryData().collect {
                _categoryData.value = it
            }
        }
    }

    fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_categoryName.value.isNotEmpty()) {
                repository.insertCategory(category = Category().apply {
                    categoryName = this@HomeViewModel._categoryName.value
                    categoryDescription = this@HomeViewModel._categoryDescription.value
                })
            }
        }
    }



}