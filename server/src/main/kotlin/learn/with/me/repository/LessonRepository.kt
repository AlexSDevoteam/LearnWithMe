package learn.with.me.repository

import learn.with.me.model.Answer
import learn.with.me.model.ApiResponse
import learn.with.me.model.Lesson
import learn.with.me.model.Question

interface LessonRepository {
    suspend fun getQuestions(lessonId: Int): ApiResponse<Question>
    suspend fun getAnswers(questionId: Int): ApiResponse<Answer>
    suspend fun getAllLessons(): ApiResponse<Lesson>
}
