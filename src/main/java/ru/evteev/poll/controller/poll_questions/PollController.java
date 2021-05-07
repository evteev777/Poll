package ru.evteev.poll.controller.poll_questions;

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
import ru.evteev.poll.dto.api.respomce.PollDTO;
import ru.evteev.poll.dto.mapper.PollMapper;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.poll_questions.PollService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PollController {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String DELETED = "Poll with ID=%s is deleted";

    private final PollService pollService;

    @GetMapping("/polls")
    public List<PollDTO> getPollList() {
        return pollService.getAllPolls().stream()
                .map(PollMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/polls/{id}")
    public PollDTO getPoll(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Poll poll = pollService.getPoll(id);
        return PollMapper.INSTANCE.toDTO(poll);
    }

    @PostMapping("/polls")
    public PollDTO createPoll(@Valid @RequestBody Poll poll, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        pollService.createOrUpdatePoll(poll);
        return PollMapper.INSTANCE.toDTO(poll);
    }

    @PutMapping("/polls")
    public PollDTO updatePoll(@Valid @RequestBody Poll poll, BindingResult br) {
        throwExceptionIfEmpty(poll.getId());
        throwExceptionIfValidationFails(br);
        pollService.createOrUpdatePoll(poll);
        return PollMapper.INSTANCE.toDTO(poll);
    }

    @DeleteMapping("/polls/{id}")
    public String deletePoll(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        pollService.deletePoll(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Poll poll = pollService.getPoll(id);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, id));
        }
    }

    private void throwExceptionIfValidationFails(BindingResult br) {
        if (br.hasErrors()) {
            throw new FieldValidationException(br.getAllErrors().toString());
        }
    }
}
