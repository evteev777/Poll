package ru.evteev.poll.service.poll_questions.embedded;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface PollQuestionsService {

    List<Question> getPollQuestions(int pollId);

    Question getPollQuestion(int pollId, int questionId);

    void createOrUpdateQuestion(int pollId, Question question);

    void deleteQuestion(int pollId, int questionId);
}
