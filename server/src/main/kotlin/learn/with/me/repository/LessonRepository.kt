package learn.with.me.repository

import learn.with.me.model.ApiResponse

interface LessonRepository {
    //    suspend fun getQuestions(lessonId: Int): ApiResponse<Question>
//    suspend fun getAnswers(questionId: Int): ApiResponse<Answer>
//    suspend fun getAllLessons(): ApiResponse<Lesson>
    suspend fun getQuestions(lessonId: Int): ApiResponse
    suspend fun getAnswers(questionId: Int): ApiResponse
    suspend fun getAllLessons(): ApiResponse
}
