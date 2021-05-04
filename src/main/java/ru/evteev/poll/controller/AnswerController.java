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
import ru.evteev.poll.dto.api.respomce.AnswerDTO;
import ru.evteev.poll.dto.mapper.AnswerMapper;
import ru.evteev.poll.entity.Answer;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AnswerController {

    private static final String NO_SUCH_VARIANT = "No such answer  with ID=%s in database";
    private static final String DELETED = "Answer  with ID=%s is deleted";

    private final AnswerService answerService;

    @GetMapping("/answers")
    public List<AnswerDTO> getAnswerList() {
        return answerService.getAllAnswers().stream()
                .map(AnswerMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/answers/{id}")
    public AnswerDTO getAnswer(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Answer answer = answerService.readAnswer(id);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @PostMapping("/answers")
    public AnswerDTO createAnswer(
            @Valid @RequestBody Answer answer, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        answerService.createOrUpdateAnswer(answer);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @PutMapping("/answers")
    public AnswerDTO updateAnswer(
            @Valid @RequestBody Answer answer, BindingResult br) {
        throwExceptionIfEmpty(answer.getId());
        throwExceptionIfValidationFails(br);
        answerService.createOrUpdateAnswer(answer);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @DeleteMapping("/answers/{id}")
    public String deleteAnswer(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        answerService.deleteAnswer(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Answer answer = answerService.readAnswer(id);
        if (answer == null) {
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
