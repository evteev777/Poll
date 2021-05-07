package ru.evteev.poll.repository.poll_questions;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> getPollQuestions(int pollId);

    Question getPollQuestion(int pollId, int questionId);

    List<Question> getAllQuestions();

    void createOrUpdateQuestion(Question question);

    Question getQuestion(int id);

    void deleteQuestion(int id);
}
