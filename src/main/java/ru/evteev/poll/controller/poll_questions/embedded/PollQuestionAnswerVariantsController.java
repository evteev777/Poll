package ru.evteev.poll.controller.poll_questions.embedded;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evteev.poll.dto.api.respomce.AnswerVariantDTO;
import ru.evteev.poll.dto.mapper.AnswerVariantMapper;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.poll_questions.AnswerVariantService;
import ru.evteev.poll.service.poll_questions.PollService;
import ru.evteev.poll.service.poll_questions.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PollQuestionAnswerVariantsController {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";
    private static final String NO_SUCH_VARIANT = "No such answer variant with ID=%s in database";

    private final PollService pollService;
    private final QuestionService questionService;
    private final AnswerVariantService answerVariantService;

    @GetMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public List<AnswerVariantDTO> getQuestionAnswerVariants(
            @PathVariable int pollId, @PathVariable int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        return answerVariantService.getQuestionAnswerVariants(pollId, questionId).stream()
                .map(AnswerVariantMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/polls/{pollId}/questions/{questionId}/answer_variants/{id}")
    public AnswerVariantDTO getQuestionAnswerVariant(
            @PathVariable int pollId, @PathVariable int questionId, @PathVariable int id) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        throwExceptionIfAnswerVariantEmpty(id);
        AnswerVariant answerVariant = answerVariantService
                .getQuestionAnswerVariant(pollId, questionId, id);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PostMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public AnswerVariantDTO createQuestionAnswerVariant(
            @PathVariable int pollId,
            @PathVariable int questionId,
            @RequestBody AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        answerVariantService.createOrUpdateAnswerVariant(questionId, answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PutMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public AnswerVariantDTO updateAnswerVariant(
            @PathVariable int pollId,
            @PathVariable int questionId,
            @RequestBody AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        throwExceptionIfAnswerVariantEmpty(answerVariant.getId());
        answerVariantService.createOrUpdateAnswerVariant(questionId, answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }


    private void throwExceptionIfPollEmpty(int pollId) {
        Poll poll = pollService.getPoll(pollId);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, pollId));
        }
    }

    private void throwExceptionIfQuestionEmpty(int questionId) {
        Question question = questionService.getQuestion(questionId);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, questionId));
        }
    }

    private void throwExceptionIfAnswerVariantEmpty(int id) {
        AnswerVariant answerVariant = answerVariantService.getAnswerVariant(id);
        if (answerVariant == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_VARIANT, id));
        }
    }
}
