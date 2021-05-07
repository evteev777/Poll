package ru.evteev.poll.service;

import ru.evteev.poll.entity.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswers();

    void createOrUpdateAnswer(Answer answer);

    Answer getAnswer(int id);

    void deleteAnswer(int id);
}
