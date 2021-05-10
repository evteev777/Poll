package ru.evteev.poll.controller.poll_questions;

import lombok.AllArgsConstructor;
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
import ru.evteev.poll.service.poll_questions.PollQuestionAnswerVariantsService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PollQuestionAnswerVariantsController {

    private static final String DELETED = "Question with ID=%s is deleted";

    private final PollQuestionAnswerVariantsService pollQuestionAnswerVariantsService;

    @GetMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public List<AnswerVariantDTO> getQuestionAnswerVariantList(
            @PathVariable int pollId, @PathVariable int questionId) {

        return pollQuestionAnswerVariantsService
                .getQuestionAnswerVariantList(pollId, questionId).stream()
                .map(AnswerVariantMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/polls/{pollId}/questions/{questionId}/answer_variants/{id}")
    public AnswerVariantDTO getQuestionAnswerVariant(
            @PathVariable int pollId, @PathVariable int questionId, @PathVariable int id) {
        return AnswerVariantMapper.INSTANCE.toDTO(
                pollQuestionAnswerVariantsService
                        .getQuestionAnswerVariant(pollId, questionId, id));
    }

    @PostMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public AnswerVariantDTO createQuestionAnswerVariant(
            @PathVariable int pollId,
            @PathVariable int questionId,
            @RequestBody AnswerVariant answerVariant) {
        pollQuestionAnswerVariantsService.createAnswerVariant(pollId, questionId, answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PutMapping("/polls/{pollId}/questions/{questionId}/answer_variants")
    public AnswerVariantDTO updateAnswerVariant(
            @PathVariable int pollId,
            @PathVariable int questionId,
            @RequestBody AnswerVariant answerVariant) {
        pollQuestionAnswerVariantsService.updateAnswerVariant(pollId, questionId, answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @DeleteMapping("/polls/{pollId}/questions/{questionId}/answer_variants/{id}")
    public String deleteAnswerVariant(
            @PathVariable int pollId,
            @PathVariable int questionId,
            @PathVariable int id) {
        pollQuestionAnswerVariantsService.deleteAnswerVariant(pollId, questionId, id);
        return String.format(DELETED, id);
    }
}
