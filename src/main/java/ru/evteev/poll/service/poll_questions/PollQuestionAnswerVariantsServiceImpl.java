package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.repository.poll_questions.PollQuestionAnswerVariantsRepository;
import ru.evteev.poll.repository.poll_questions.PollQuestionsRepository;
import ru.evteev.poll.repository.poll_questions.PollRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PollQuestionAnswerVariantsServiceImpl
        implements PollQuestionAnswerVariantsService {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";
    private static final String NO_SUCH_VARIANT = "No such answer variant with ID=%s in database";

    private final PollRepository pollRepository;
    private final PollQuestionsRepository pollQuestionsRepository;
    private final PollQuestionAnswerVariantsRepository answerVariantRepository;

    @Override
    @Transactional
    public List<AnswerVariant> getQuestionAnswerVariantList(int pollId, int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        return answerVariantRepository.getQuestionAnswerVariants(pollId, questionId);
    }

    @Override
    @Transactional
    public AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, id);
        return answerVariantRepository
                .getQuestionAnswerVariant(pollId, questionId, id);
    }

    @Override
    @Transactional
    public void createAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        answerVariant.setQuestion(pollQuestionsRepository.getPollQuestion(pollId, questionId));
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void updateAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, answerVariant.getId());
        answerVariant.setQuestion(pollQuestionsRepository.getPollQuestion(pollId, questionId));
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int pollId, int questionId, int id) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, id);
        answerVariantRepository.deleteAnswerVariant(questionId, id);
    }

    private void throwExceptionIfPollEmpty(int pollId) {
        Poll poll = pollRepository.getPoll(pollId);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, pollId));
        }
    }

    private void throwExceptionIfQuestionEmpty(int pollId, int questionId) {
        Question question = pollQuestionsRepository.getPollQuestion(pollId, questionId);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, questionId));
        }
    }

    private void throwExceptionIfAnswerVariantEmpty(int pollId, int questionId, int id) {
        AnswerVariant answerVariant = answerVariantRepository
                .getQuestionAnswerVariant(pollId, questionId, id);
        if (answerVariant == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_VARIANT, id));
        }
    }
}
