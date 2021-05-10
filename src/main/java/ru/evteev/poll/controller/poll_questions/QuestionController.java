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
import ru.evteev.poll.dto.api.respomce.QuestionDTO;
import ru.evteev.poll.dto.mapper.QuestionMapper;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.service.poll_questions.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuestionController {

    private static final String DELETED = "Question with ID=%s is deleted";

    private final QuestionService questionService;

    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions().stream()
                .map(QuestionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO getQuestion(@PathVariable int id) {
        return QuestionMapper.INSTANCE.toDTO(
                questionService.getQuestion(id));
    }

    @PostMapping("/questions")
    public QuestionDTO createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @PutMapping("/questions")
    public QuestionDTO updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @DeleteMapping("/questions/{id}")
    public String deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return String.format(DELETED, id);
    }
}
