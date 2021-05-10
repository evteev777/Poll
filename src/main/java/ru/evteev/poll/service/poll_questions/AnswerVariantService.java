package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantService {

    List<AnswerVariant> getAllAnswerVariants();

    AnswerVariant getAnswerVariant(int id);

    void createAnswerVariant(AnswerVariant answerVariant);

    void updateAnswerVariant(AnswerVariant answerVariant);

    void deleteAnswerVariant(int id);
}
