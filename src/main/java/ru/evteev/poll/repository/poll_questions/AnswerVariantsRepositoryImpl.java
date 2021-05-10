package ru.evteev.poll.repository.poll_questions;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

@AllArgsConstructor
@Repository
public class AnswerVariantsRepositoryImpl
        implements AnswerVariantsRepository {

    private static final String POLL_ID = "pollId";
    private static final String QUESTION_ID = "questionId";
    private static final String ANSWER_VARIANT_ID = "id";

    private final SessionFactory sessionFactory;

    @Override
    public List<AnswerVariant> getAnswerVariantList(int pollId, int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery("from AnswerVariant a " +
                "where a.question.poll.id = :pollId and a.question.id = :questionId " +
                "order by id", AnswerVariant.class);
        query.setParameter(POLL_ID, pollId);
        query.setParameter(QUESTION_ID, questionId);
        return query.getResultList();
    }

    @Override
    public AnswerVariant getAnswerVariant(int pollId, int questionId, int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery("from AnswerVariant a " +
                "where a.question.poll.id = :pollId and a.question.id = :questionId and a.id = :id " +
                "order by id", AnswerVariant.class);
        query.setParameter(POLL_ID, pollId);
        query.setParameter(QUESTION_ID, questionId);
        query.setParameter(ANSWER_VARIANT_ID, id);
        return query.getSingleResult();
    }

    @Override
    public void createOrUpdateAnswerVariant(AnswerVariant answerVariant) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(answerVariant);
    }

    @Override
    public void deleteAnswerVariant(int questionId, int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery("delete from AnswerVariant a " +
                "where a.question.id = :questionId and id = :id");
        query.setParameter(QUESTION_ID, questionId);
        query.setParameter(ANSWER_VARIANT_ID, id);
        query.executeUpdate();
    }
}
