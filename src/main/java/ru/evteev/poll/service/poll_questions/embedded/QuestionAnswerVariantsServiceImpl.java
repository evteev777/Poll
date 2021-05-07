package ru.evteev.poll.service.poll_questions.embedded;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.repository.poll_questions.AnswerVariantRepository;
import ru.evteev.poll.repository.poll_questions.QuestionRepository;
import ru.evteev.poll.repository.poll_questions.embedded.QuestionAnswerVariantsRepository;
import ru.evteev.poll.service.poll_questions.AnswerVariantService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionAnswerVariantsServiceImpl
        implements QuestionAnswerVariantsService {

    private final QuestionRepository questionRepository;
    private final QuestionAnswerVariantsRepository answerVariantRepository;

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
    public void createOrUpdateAnswerVariant(int questionId, AnswerVariant answerVariant) {
        answerVariant.setQuestion(questionRepository.getQuestion(questionId));
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int questionId, int id) {
        answerVariantRepository.deleteAnswerVariant(questionId, id);
    }
}
