package ru.evteev.poll.service.poll_questions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.repository.poll_questions.PollRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PollServiceImpl implements PollService {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";

    private final PollRepository pollRepository;

    @Override
    @Transactional
    public List<Poll> getAllPolls() {
        return pollRepository.getAllPolls();
    }

    @Override
    @Transactional
    public Poll getPoll(int id) {
        throwExceptionIfEmpty(id);
        return pollRepository.getPoll(id);
    }

    @Override
    @Transactional
    public Poll createPoll(Poll poll) {
        pollRepository.createOrUpdatePoll(poll);
        return poll;
    }

    @Override
    @Transactional
    public Poll updatePoll(Poll poll) {
        throwExceptionIfEmpty(poll.getId());
        pollRepository.createOrUpdatePoll(poll);
        return poll;
    }

    @Override
    @Transactional
    public void deletePoll(int id) {
        throwExceptionIfEmpty(id);
        pollRepository.deletePoll(id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Poll poll = pollRepository.getPoll(id);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, id));
        }
    }
}
