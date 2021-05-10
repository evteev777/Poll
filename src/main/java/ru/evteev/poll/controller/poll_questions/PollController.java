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
import ru.evteev.poll.dto.api.respomce.PollDTO;
import ru.evteev.poll.dto.mapper.PollMapper;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.service.poll_questions.PollService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PollController {

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
        return PollMapper.INSTANCE.toDTO(
                pollService.getPoll(id));
    }

    @PostMapping("/polls")
    public PollDTO createPoll(@RequestBody Poll poll) {
        pollService.createPoll(poll);
        return PollMapper.INSTANCE.toDTO(poll);
    }

    @PutMapping("/polls")
    public PollDTO updatePoll(@RequestBody Poll poll) {
        pollService.updatePoll(poll);
        return PollMapper.INSTANCE.toDTO(poll);
    }

    @DeleteMapping("/polls/{id}")
    public String deletePoll(@PathVariable int id) {
        pollService.deletePoll(id);
        return String.format(DELETED, id);
    }
}
