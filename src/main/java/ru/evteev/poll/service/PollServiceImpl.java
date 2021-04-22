package ru.evteev.poll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.repository.PollRepository;
import ru.evteev.poll.entity.Poll;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    @Override
    @Transactional
    public List<Poll> getAllPolls() {
        return pollRepository.getAllPolls();
    }

    @Override
    @Transactional
    public Poll createOrUpdatePoll(Poll poll) {
        pollRepository.createOrUpdatePoll(poll);
        return poll;
    }

    @Override
    @Transactional
    public Poll readPoll(int id) {
        return pollRepository.getPoll(id);
    }

    @Override
    @Transactional
    public void deletePoll(int id) {
        pollRepository.deletePoll(id);
    }
}
