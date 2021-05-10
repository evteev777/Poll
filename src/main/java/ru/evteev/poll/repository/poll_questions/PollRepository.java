package ru.evteev.poll.repository.poll_questions;

import ru.evteev.poll.entity.Poll;

import java.util.List;

public interface PollRepository {

    List<Poll> getAllPolls();

    Poll getPoll(int id);

    void createOrUpdatePoll(Poll poll);

    void deletePoll(int id);
}
