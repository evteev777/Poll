package ru.evteev.poll.service;

import ru.evteev.poll.entity.Poll;

import java.util.List;

public interface PollService {

    List<Poll> getAllPolls();

    Poll createOrUpdatePoll(Poll poll);

    Poll getPoll(int id);

    void deletePoll(int id);
}
