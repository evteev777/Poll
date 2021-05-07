package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.AnswerVariant;

import java.util.List;

@AllArgsConstructor
@Repository
public class AnswerVariantRepositoryImpl implements AnswerVariantRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<AnswerVariant> getQuestionAnswerVariants(int pollId, int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery("from AnswerVariant a " +
                "where a.question.poll.id = :pollId and a.question.id = :questionId " +
                "order by id", AnswerVariant.class);
        query.setParameter("pollId", pollId);
        query.setParameter("questionId", questionId);
        return query.getResultList();
    }

    @Override
    public AnswerVariant getQuestionAnswerVariant(int pollId, int questionId, int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery("from AnswerVariant a " +
                "where a.question.poll.id = :pollId and a.question.id = :questionId and a.id = :id " +
                "order by id", AnswerVariant.class);
        query.setParameter("pollId", pollId);
        query.setParameter("questionId", questionId);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<AnswerVariant> getAllAnswerVariants() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AnswerVariant order by id",
                AnswerVariant.class).getResultList();
    }

    @Override
    public void createOrUpdateAnswerVariant(AnswerVariant answerVariant) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(answerVariant);
    }

    @Override
    public AnswerVariant getAnswerVariant(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AnswerVariant.class, id);
    }

    @Override
    public void deleteAnswerVariant(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<AnswerVariant> query = session.createQuery(
                "delete from AnswerVariant where id = :answerVariantId");
        query.setParameter("answerVariantId", id);
        query.executeUpdate();
    }
}
