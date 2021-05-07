package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Question;

import java.util.List;

@AllArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

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
    public List<Question> getAllQuestions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Question order by id", Question.class)
                .getResultList();
    }

    @Override
    public void createOrUpdateQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(question);
    }

    @Override
    public Question getQuestion(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Question.class, id);
    }

    @Override
    public void deleteQuestion(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery(
                "delete from Question where id = :questionId");
        query.setParameter("questionId", id);
        query.executeUpdate();
    }
}
