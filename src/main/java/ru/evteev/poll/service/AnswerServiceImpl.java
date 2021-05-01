package ru.evteev.poll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Answer;
import ru.evteev.poll.repository.AnswerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public List<Answer> getAllAnswers() {
        return answerRepository.getAllAnswers();
    }

    @Override
    @Transactional
    public void createOrUpdateAnswer(Answer answer) {
        answerRepository.createOrUpdateAnswer(answer);
    }

    @Override
    @Transactional
    public Answer readAnswer(int id) {
        return answerRepository.getAnswer(id);
    }

    @Override
    @Transactional
    public void deleteAnswer(int id) {
        answerRepository.deleteAnswer(id);
    }
}
