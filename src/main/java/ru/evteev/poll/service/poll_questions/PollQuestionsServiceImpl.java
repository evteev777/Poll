package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.repository.poll_questions.PollQuestionsRepository;
import ru.evteev.poll.repository.poll_questions.PollRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PollQuestionsServiceImpl implements PollQuestionsService {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";

    private final PollRepository pollRepository;
    private final PollQuestionsRepository pollQuestionsRepository;

    @Override
    @Transactional
    public List<Question> getPollQuestions(int pollId) {
        throwExceptionIfPollEmpty(pollId);
        return pollQuestionsRepository.getPollQuestions(pollId);
    }

    @Override
    @Transactional
    public Question getPollQuestion(int pollId, int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        return pollQuestionsRepository.getPollQuestion(pollId, questionId);
    }

    @Override
    @Transactional
    public void createQuestion(int pollId, Question question) {
        throwExceptionIfPollEmpty(pollId);
        question.setPoll(pollRepository.getPoll(pollId));
        pollQuestionsRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public void updateQuestion(int pollId, Question question) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, question.getId());
        question.setPoll(pollRepository.getPoll(pollId));
        pollQuestionsRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public void deleteQuestion(int pollId, int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        pollQuestionsRepository.deleteQuestion(pollId, questionId);
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
}
