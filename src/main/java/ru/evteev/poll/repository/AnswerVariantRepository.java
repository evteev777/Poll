package ru.evteev.poll.repository;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantRepository {

    List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId);

    List<AnswerVariant> getAllAnswerVariants();

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    AnswerVariant getAnswerVariant(int id);

    void deleteAnswerVariant(int id);
}
