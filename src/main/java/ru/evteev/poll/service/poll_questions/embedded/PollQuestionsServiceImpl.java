package ru.evteev.poll.service.poll_questions.embedded;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.repository.poll_questions.PollRepository;
import ru.evteev.poll.repository.poll_questions.embedded.PollQuestionsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PollQuestionsServiceImpl implements PollQuestionsService {

    private final PollRepository pollRepository;
    private final PollQuestionsRepository pollQuestionsRepository;

    @Override
    @Transactional
    public List<Question> getPollQuestions(int pollId) {
        return pollQuestionsRepository.getPollQuestions(pollId);
    }

    @Override
    @Transactional
    public Question getPollQuestion(int pollId, int questionId) {
        return pollQuestionsRepository.getPollQuestion(pollId, questionId);
    }

    @Override
    @Transactional
    public void createOrUpdateQuestion(int pollId, Question question) {
        question.setPoll(pollRepository.getPoll(pollId));
        pollQuestionsRepository.createOrUpdateQuestion(question);
    }

    @Override
    public void deleteQuestion(int pollId, int questionId) {
        pollQuestionsRepository.deleteQuestion(pollId, questionId);
    }
}
