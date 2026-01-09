package learn.with.me.repository

import learn.with.me.model.Answer
import learn.with.me.model.Lesson
import learn.with.me.model.Question

interface LessonRepository {
    suspend fun getQuestions(lessonId: Int): List<Question>
    suspend fun getAnswers(questionId: Int): List<Answer>
    suspend fun getAllLessons(): List<Lesson>
}
