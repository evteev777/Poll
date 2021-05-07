package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getPollQuestions(int pollId);

    Question getPollQuestion(int pollId, int questionId);

    void createOrUpdateQuestion(int pollId, Question question);

    List<Question> getAllQuestions();

    void createOrUpdateQuestion(Question question);

    Question getQuestion(int id);

    void deleteQuestion(int id);
}
