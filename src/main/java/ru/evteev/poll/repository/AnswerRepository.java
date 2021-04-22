package ru.evteev.poll.repository;

import ru.evteev.poll.entity.Answer;

import java.util.List;

public interface AnswerRepository {

    List<Answer> getAllAnswers();

    void createOrUpdateAnswer(Answer answer);

    Answer getAnswer(int id);

    void deleteAnswer(int id);
}
