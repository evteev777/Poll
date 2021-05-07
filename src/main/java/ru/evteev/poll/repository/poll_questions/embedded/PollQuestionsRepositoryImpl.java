package ru.evteev.poll.repository.poll_questions.embedded;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Question;

import java.util.List;

@AllArgsConstructor
@Repository
public class PollQuestionsRepositoryImpl implements PollQuestionsRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Question> getPollQuestions(int pollId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery(
                "from Question q where q.poll.id = :pollId order by id", Question.class);
        query.setParameter("pollId", pollId);
        return query.getResultList();
    }

    @Override
    public Question getPollQuestion(int pollId, int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery("from Question q " +
                "where q.id = :questionId and q.poll.id = :pollId " +
                "order by id", Question.class);
        query.setParameter("pollId", pollId);
        query.setParameter("questionId", questionId);
        return query.getSingleResult();
    }

    @Override
    public void createOrUpdateQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(question);
    }

    @Override
    public void deleteQuestion(int pollId, int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery("delete from Question q " +
                "where q.poll.id = :pollId and q.id = :questionId");
        query.setParameter("pollId", pollId);
        query.setParameter("questionId", questionId);
        query.executeUpdate();
    }
}
