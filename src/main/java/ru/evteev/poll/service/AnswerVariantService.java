package ru.evteev.poll.service;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantService {

    List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId);

    List<AnswerVariant> getAllAnswerVariants();

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    AnswerVariant getAnswerVariant(int id);

    void deleteAnswerVariant(int id);
}
