package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.repository.poll_questions.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";

    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    @Override
    @Transactional
    public Question getQuestion(int id) {
        throwExceptionIfQuestionEmpty(id);
        return questionRepository.getQuestion(id);
    }

    @Override
    @Transactional
    public void createQuestion(Question question) {
        questionRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public void updateQuestion(Question question) {
        throwExceptionIfQuestionEmpty(question.getId());
        questionRepository.createOrUpdateQuestion(question);
    }

    @Override
    @Transactional
    public void deleteQuestion(int id) {
        throwExceptionIfQuestionEmpty(id);
        questionRepository.deleteQuestion(id);
    }

    private void throwExceptionIfQuestionEmpty(int id) {
        Question question = questionRepository.getQuestion(id);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, id));
        }
    }
}
