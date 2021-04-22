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
import ru.evteev.poll.dto.api.respomce.QuestionDTO;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.mapper.QuestionMapper;
import ru.evteev.poll.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuestionController {

    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";
    private static final String DELETED = "Question with ID=%s is deleted";

    private final QuestionService questionService;

    @GetMapping("/questions")
    public List<QuestionDTO> getQuestionList() {
        return questionService.getAllQuestions().stream()
                .map(QuestionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO getQuestion(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Question question = questionService.readQuestion(id);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @PostMapping("/questions")
    public QuestionDTO createQuestion(@Valid @RequestBody Question question, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        questionService.createOrUpdateQuestion(question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @PutMapping("/questions")
    public QuestionDTO updateQuestion(@Valid @RequestBody Question question, BindingResult br) {
        throwExceptionIfEmpty(question.getId());
        throwExceptionIfValidationFails(br);
        questionService.createOrUpdateQuestion(question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @DeleteMapping("/questions/{id}")
    public String deleteQuestion(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        questionService.deleteQuestion(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Question question = questionService.readQuestion(id);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, id));
        }
    }

    private void throwExceptionIfValidationFails(BindingResult br) {
        if (br.hasErrors()) {
            throw new FieldValidationException(br.getAllErrors().toString());
        }
    }
}
