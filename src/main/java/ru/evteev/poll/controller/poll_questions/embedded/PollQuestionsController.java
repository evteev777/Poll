package ru.evteev.poll.controller.poll_questions.embedded;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evteev.poll.dto.api.respomce.QuestionDTO;
import ru.evteev.poll.dto.mapper.QuestionMapper;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.poll_questions.PollService;
import ru.evteev.poll.service.poll_questions.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PollQuestionsController {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";

    private final PollService pollService;
    private final QuestionService questionService;

    @GetMapping("/polls/{pollId}/questions")
    public List<QuestionDTO> getPollQuestions(@PathVariable int pollId) {
        throwExceptionIfPollEmpty(pollId);
        return questionService.getPollQuestions(pollId).stream()
                .map(QuestionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/polls/{pollId}/questions/{questionId}")
    public QuestionDTO getPollQuestion(@PathVariable int pollId, @PathVariable int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        Question question = questionService.getPollQuestion(pollId, questionId);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    private void throwExceptionIfPollEmpty(int pollId) {
        Poll poll = pollService.getPoll(pollId);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, pollId));
        }
    }

    private void throwExceptionIfQuestionEmpty(int id) {
        Question question = questionService.getQuestion(id);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, id));
        }
    }
}
