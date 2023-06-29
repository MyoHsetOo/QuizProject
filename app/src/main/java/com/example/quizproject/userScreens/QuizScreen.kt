package com.example.quizproject.userScreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import com.example.quizproject.viewModel.QuestionSetViewModel
import com.example.quizproject.viewModel.QuizViewModel
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList


@Composable
fun QuizScreen( navController: NavController ) {

    var repository = MongoRepositoryImpl()

    var viewModelQuizScreen : QuizViewModel = QuizViewModel(repository)

    var quizData = viewModelQuizScreen._quizData

    var viewModel : QuestionSetViewModel = QuestionSetViewModel( repository )

   // var obj : BsonObjectId? = id?.let { BsonObjectId(it) }


    RandomItemFromList(itemList = quizData.value)
    Text(text = "Hello")





}


@Composable
fun RandomItemFromList(itemList: List<QuestionSet>) {
    var randomItem by remember { mutableStateOf("") }

    LaunchedEffect(itemList) {
        randomItem = (itemList.randomOrNull() ?: "").toString()
    }

    // Display the random item
    Text(text = randomItem)
}

/*@Composable
fun RandomListFromRealm() {
    var randomList by remember { mutableStateOf(emptyList<QuestionSet>()) }

    val realm = Realm.getInstance(LocalContext.current)

    LaunchedEffect(Unit) {
        val allObjects = realm.where<QuestionSet>().findAll().toList()
        randomList = allObjects.shuffled(Random)
    }

    // Display the random list
    randomList.forEach { item ->
        Text(text = item.toString())
    }
}*/

/*
@Composable
fun RandomIdFromRealm() {
    var randomId by remember { mutableStateOf("") }

    val realm = Realm.getInstance(LocalContext.current)

    LaunchedEffect(Unit) {
        val randomObject = realm.where<QuestionSet>().findAll().randomOrNull()
        randomId = randomObject?.id ?: generateRandomId()
    }

    // Display the random ID
    Text(text = randomId)
}

private fun generateRandomId(): String {
    return UUID.randomUUID().toString()
}*/
