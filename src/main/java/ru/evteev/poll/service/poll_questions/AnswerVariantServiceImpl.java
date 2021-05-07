package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.repository.poll_questions.AnswerVariantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {

    private final AnswerVariantRepository answerVariantRepository;

    @Override
    @Transactional
    public List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId) {
        return answerVariantRepository.getQuestionAnswerVariants(pollId, questionId);
    }

    @Override
    @Transactional
    public AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id) {
        return answerVariantRepository
                .getQuestionAnswerVariant(pollId, questionId, id);
    }

    @Override
    @Transactional
    public List<AnswerVariant> getAllAnswerVariants() {
        return answerVariantRepository.getAllAnswerVariants();
    }

    @Override
    @Transactional
    public void createOrUpdateAnswerVariant(AnswerVariant answerVariant) {
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public AnswerVariant getAnswerVariant(int id) {
        return answerVariantRepository.getAnswerVariant(id);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int id) {
        answerVariantRepository.deleteAnswerVariant(id);
    }
}
