package ru.evteev.poll.repository.poll_questions.embedded;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface QuestionAnswerVariantsRepository {

    List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId);

    AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id);

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    void deleteAnswerVariant(int questionId, int id);
}
