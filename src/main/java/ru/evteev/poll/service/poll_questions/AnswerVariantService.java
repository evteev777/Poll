package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantService {

    List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId);

    AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id);

    void createOrUpdateAnswerVariant(int questionId, AnswerVariant answerVariant);

    List<AnswerVariant> getAllAnswerVariants();

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    AnswerVariant getAnswerVariant(int id);

    void deleteAnswerVariant(int id);
}
