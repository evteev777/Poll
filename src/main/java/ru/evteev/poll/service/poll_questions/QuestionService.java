package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestion(int id);

    void createQuestion(Question question);

    void updateQuestion(Question question);

    void deleteQuestion(int id);
}
