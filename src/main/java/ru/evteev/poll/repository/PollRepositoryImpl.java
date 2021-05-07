package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Poll;

import java.util.List;

@Repository
@AllArgsConstructor
@Getter
@Setter
public class PollRepositoryImpl implements PollRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Poll> getAllPolls() {
        Session session = sessionFactory.getCurrentSession();
        List<Poll> pollList = session.createQuery("from Poll order by id", Poll.class).getResultList();
        pollList.get(0); // EAGER get polls
        return pollList;
    }

    @Override
    public void createOrUpdatePoll(Poll poll) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(poll);
    }

    @Override
    public Poll getPoll(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Poll.class, id);
    }

    @Override
    public void deletePoll(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Poll> query = session.createQuery(
                "delete from Poll where id = :pollId");
        query.setParameter("pollId", id);
        query.executeUpdate();
    }
}
