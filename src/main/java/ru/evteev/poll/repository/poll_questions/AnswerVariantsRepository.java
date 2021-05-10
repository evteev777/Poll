package ru.evteev.poll.repository.poll_questions;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantsRepository {

    List<AnswerVariant> getAnswerVariantList(int pollId, int questionId);

    AnswerVariant getAnswerVariant(int pollId, int questionId, int id);

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    void deleteAnswerVariant(int questionId, int id);
}
