package ru.evteev.poll.service.poll_questions;

import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

public interface AnswerVariantService {

    List<AnswerVariant> getAllAnswerVariants();

    void createOrUpdateAnswerVariant(AnswerVariant answerVariant);

    AnswerVariant getAnswerVariant(int id);

    void deleteAnswerVariant(int id);
}
