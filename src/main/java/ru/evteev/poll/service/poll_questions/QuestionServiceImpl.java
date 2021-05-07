package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.repository.poll_questions.PollRepository;
import ru.evteev.poll.repository.poll_questions.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final PollRepository pollRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public List<Question> getPollQuestions(int pollId) {
        return questionRepository.getPollQuestions(pollId);
    }

    @Override
    @Transactional
    public Question getPollQuestion(int pollId, int questionId) {
        return questionRepository.getPollQuestion(pollId, questionId);
    }

    @Override
    @Transactional
    public void createOrUpdateQuestion(int pollId, Question question) {
        question.setPoll(pollRepository.getPoll(pollId));
        questionRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public void deleteQuestion(int pollId, int questionId) {
        questionRepository.deleteQuestion(pollId, questionId);
    }

    @Override
    @Transactional
    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    @Override
    @Transactional
    public void createOrUpdateQuestion(Question question) {
        questionRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public Question getQuestion(int id) {
        return questionRepository.getQuestion(id);
    }

    @Override
    @Transactional
    public void deleteQuestion(int id) {
        questionRepository.deleteQuestion(id);
    }
}
