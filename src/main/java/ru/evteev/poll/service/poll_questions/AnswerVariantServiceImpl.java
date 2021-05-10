package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.repository.poll_questions.AnswerVariantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {

    private static final String NO_SUCH_VARIANT = "No such answer variant with ID=%s in database";

    private final AnswerVariantRepository answerVariantRepository;

    @Override
    @Transactional
    public List<AnswerVariant> getAllAnswerVariants() {
        return answerVariantRepository.getAllAnswerVariants();
    }

    @Override
    @Transactional
    public AnswerVariant getAnswerVariant(int id) {
        throwExceptionIfAnswerVariantEmpty(id);
        return answerVariantRepository.getAnswerVariant(id);
    }

    @Override
    @Transactional
    public void createAnswerVariant(AnswerVariant answerVariant) {
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void updateAnswerVariant(AnswerVariant answerVariant) {
        throwExceptionIfAnswerVariantEmpty(answerVariant.getId());
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int id) {
        throwExceptionIfAnswerVariantEmpty(id);
        answerVariantRepository.deleteAnswerVariant(id);
    }


    private void throwExceptionIfAnswerVariantEmpty(int id) {
        AnswerVariant answerVariant = answerVariantRepository.getAnswerVariant(id);
        if (answerVariant == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_VARIANT, id));
        }
    }
}
