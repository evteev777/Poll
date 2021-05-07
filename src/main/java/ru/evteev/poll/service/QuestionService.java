package ru.evteev.poll.service;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getPollQuestions(int pollId);

    List<Question> getAllQuestions();

    void createOrUpdateQuestion(Question question);

    Question getQuestion(int id);

    void deleteQuestion(int id);
}
