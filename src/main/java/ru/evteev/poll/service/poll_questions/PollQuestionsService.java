package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface PollQuestionsService {

    List<Question> getPollQuestions(int pollId);

    Question getPollQuestion(int pollId, int questionId);

    void createQuestion(int pollId, Question question);

    void updateQuestion(int pollId, Question question);

    void deleteQuestion(int pollId, int questionId);
}
