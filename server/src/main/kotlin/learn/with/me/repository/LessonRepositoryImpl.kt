package learn.with.me.repository

import learn.with.me.fake_data.FakeData
import learn.with.me.model.ApiResponse

class LessonRepositoryImpl : LessonRepository {
    override suspend fun getQuestions(lessonId: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
//            valuesList = FakeData.questions.filter { it.moduleId.contains(lessonId) },
            lastUpdated = System.currentTimeMillis()
        )
    }

    override suspend fun getAnswers(questionId: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
//            valuesList = FakeData.answers.filter { it.questionId.contains(questionId) },
            lastUpdated = System.currentTimeMillis()
        )
    }

    override suspend fun getAllLessons(): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            valuesList = FakeData.lessons,
            lastUpdated = System.currentTimeMillis()
        )
    }
}