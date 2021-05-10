package ru.evteev.poll.repository.poll_questions;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Question;

import java.util.List;

@AllArgsConstructor
@Repository
public class QuestionsRepositoryImpl implements QuestionsRepository {

    private static final String POLL_ID = "pollId";
    private static final String QUESTION_ID = "questionId";

    private final SessionFactory sessionFactory;

    @Override
    public List<Question> getQuestionList(int pollId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery(
                "from Question q where q.poll.id = :pollId order by id", Question.class);
        query.setParameter(POLL_ID, pollId);
        return query.getResultList();
    }

    @Override
    public Question getQuestion(int pollId, int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Question> query = session.createQuery("from Question q " +
                "where q.id = :questionId and q.poll.id = :pollId " +
                "order by id", Question.class);
        query.setParameter(POLL_ID, pollId);
        query.setParameter(QUESTION_ID, questionId);
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
        query.setParameter(POLL_ID, pollId);
        query.setParameter(QUESTION_ID, questionId);
        query.executeUpdate();
    }
}
