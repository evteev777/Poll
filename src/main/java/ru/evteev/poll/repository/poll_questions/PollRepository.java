package ru.evteev.poll.repository.poll_questions;

import ru.evteev.poll.entity.Poll;

import java.util.List;

public interface PollRepository {

    List<Poll> getAllPolls();

    void createOrUpdatePoll(Poll poll);

    Poll getPoll(int id);

    void deletePoll(int id);
}