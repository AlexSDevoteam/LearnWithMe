package learn.with.me.fake_data

import learn.with.me.model.Answer
import learn.with.me.model.Lesson
import learn.with.me.model.Question

object FakeData {
    val answers = listOf(
        Answer(
            id = 1,
            text = "Paris",
            questionId = listOf(1)
        ),
        Answer(
            id = 2,
            text = "London",
            questionId = listOf(1)
        ),
        Answer(
            id = 3,
            text = "Berlin",
            questionId = listOf(1)
        ),
        Answer(
            id = 4,
            text = "Madrid",
            questionId = listOf(1, 2)
        ),
        Answer(
            id = 5,
            text = "Rome",
            questionId = listOf(2)
        ),
        Answer(
            id = 6,
            text = "Vienna",
            questionId = listOf(2)
        ),
        Answer(
            id = 7,
            text = "Brussels",
            questionId = listOf(2)
        )
    )
    val questions = listOf(
        Question(
            id = 1,
            text = "What is the capital of France?",
            answers = answers.filter { it.questionId.contains(1) },
            correctAnswerId = 1,
            moduleId = listOf(1)
        ),
        Question(
            id = 2,
            text = "What is the capital of Spain?",
            answers = answers.filter { it.questionId.contains(2) },
            correctAnswerId = 4,
            moduleId = listOf(1)
        )
    )
    val lessons = listOf(
        Lesson(
            id = 1,
            title = "Geography",
            description = "Learn about the world's geography",
            imageUrl = "https://example.com/geography.jpg"
        ),
        Lesson(
            id = 2,
            title = "History",
            description = "Learn about the world's history",
            imageUrl = "https://example.com/history.jpg"
        )
    )
}