package ru.evteev.poll.service.poll_questions.embedded;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface QuestionAnswerVariantsService {

    List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId);

    AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id);

    void createOrUpdateAnswerVariant(int questionId, AnswerVariant answerVariant);

    void deleteAnswerVariant(int questionId, int id);
}
