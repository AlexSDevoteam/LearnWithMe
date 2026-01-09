package learn.with.me.repository

import learn.with.me.fake_data.FakeData
import learn.with.me.model.Answer
import learn.with.me.model.Lesson
import learn.with.me.model.Question

class LessonRepositoryImpl : LessonRepository {
    override suspend fun getQuestions(lessonId: Int): List<Question> {
        return FakeData.questions.filter { it.moduleId.contains(lessonId) }
    }

    override suspend fun getAnswers(questionId: Int): List<Answer> {
        return FakeData.answers.filter { it.questionId.contains(questionId) }
    }

    override suspend fun getAllLessons(): List<Lesson> {
        return FakeData.lessons
    }
}