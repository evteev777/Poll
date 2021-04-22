package ru.evteev.poll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.repository.AnswerVariantRepository;
import ru.evteev.poll.entity.AnswerVariant;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {

    private final AnswerVariantRepository answerVariantRepository;

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
    public AnswerVariant readAnswerVariant(int id) {
        return answerVariantRepository.getAnswerVariant(id);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int id) {
        answerVariantRepository.deleteAnswerVariant(id);
    }
}
