package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface PollQuestionAnswerVariantsService {

    List<AnswerVariant> getQuestionAnswerVariantList(int pollId, int questionId);

    AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id);

    void createAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant);

    void updateAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant);

    void deleteAnswerVariant(int pollId, int questionId, int id);
}
