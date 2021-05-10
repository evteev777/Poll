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
import ru.evteev.poll.service.poll_questions.AnswerVariantService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AnswerVariantController {

    private static final String DELETED = "Answer variant with ID=%s is deleted";

    private final AnswerVariantService answerVariantService;

    @GetMapping("/answer_variants")
    public List<AnswerVariantDTO> getAnswerVariants() {
        return answerVariantService.getAllAnswerVariants().stream()
                .map(AnswerVariantMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/answer_variants/{id}")
    public AnswerVariantDTO getAnswerVariant(@PathVariable int id) {
        return AnswerVariantMapper.INSTANCE.toDTO(
                answerVariantService.getAnswerVariant(id));
    }

    @PostMapping("/answer_variants")
    public AnswerVariantDTO createAnswerVariant(@RequestBody AnswerVariant answerVariant) {
        answerVariantService.createAnswerVariant(answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @PutMapping("/answer_variants")
    public AnswerVariantDTO updateAnswerVariant(@RequestBody AnswerVariant answerVariant) {
        answerVariantService.updateAnswerVariant(answerVariant);
        return AnswerVariantMapper.INSTANCE.toDTO(answerVariant);
    }

    @DeleteMapping("/answer_variants/{id}")
    public String deleteAnswerVariant(@PathVariable int id) {
        answerVariantService.deleteAnswerVariant(id);
        return String.format(DELETED, id);
    }
}
