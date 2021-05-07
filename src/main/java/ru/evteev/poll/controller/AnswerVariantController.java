package ru.evteev.poll.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.AnswerVariantService;
import ru.evteev.poll.service.PollService;
import ru.evteev.poll.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AnswerVariantController {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";
    private static final String NO_SUCH_VARIANT = "No such answer variant with ID=%s in database";
    private static final String DELETED = "Answer variant with ID=%s is deleted";

    private final PollService pollService;
    private final QuestionService questionService;
    private final AnswerVariantService answerVariantService;

    @GetMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public List<AnswerVariantDTO> getPollQuestions(@PathVariable int pollId, @PathVariable int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(questionId);
        return answerVariantService.getQuestionAnswerVariants(pollId, questionId).stream()
                .map(AnswerVariantMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    // Standard CRUD methods:

    @GetMapping("/answer_variants")
    public List<AnswerVariantDTO> getAnswerVariants() {
        return answerVariantService.getAllAnswerVariants().stream()
                .map(AnswerVariantMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/answer_variants/{id}")
    public AnswerVariantDTO getAnswerVariant(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        AnswerVariant answerVariant = answerVariantService.readAnswerVariant(id);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PostMapping("/answer_variants")
    public AnswerVariantDTO createAnswerVariant(
            @Valid @RequestBody AnswerVariant answerVariant, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        answerVariantService.createOrUpdateAnswerVariant(answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PutMapping("/answer_variants")
    public AnswerVariantDTO updateAnswerVariant(
            @Valid @RequestBody AnswerVariant answerVariant, BindingResult br) {
        throwExceptionIfEmpty(answerVariant.getId());
        throwExceptionIfValidationFails(br);
        answerVariantService.createOrUpdateAnswerVariant(answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @DeleteMapping("/answer_variants/{id}")
    public String deleteAnswerVariant(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        answerVariantService.deleteAnswerVariant(id);
        return String.format(DELETED, id);
    }


    private void throwExceptionIfPollEmpty(int pollId) {
        Poll poll = pollService.readPoll(pollId);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, pollId));
        }
    }

    private void throwExceptionIfQuestionEmpty(int id) {
        Question question = questionService.readQuestion(id);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, id));
        }
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        AnswerVariant answerVariant = answerVariantService.readAnswerVariant(id);
        if (answerVariant == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_VARIANT, id));
        }
    }

    private void throwExceptionIfValidationFails(BindingResult br) {
        if (br.hasErrors()) {
            throw new FieldValidationException(br.getAllErrors().toString());
        }
    }
}
