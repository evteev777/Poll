package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Answer;

import java.util.List;

@AllArgsConstructor
@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private final SessionFactory sessionFactory;

    @Override

    public List<Answer> getAllAnswers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Answer order by id",
                Answer.class).getResultList();
    }

    @Override
    public void createOrUpdateAnswer(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(answer);
    }

    @Override
    public Answer getAnswer(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Answer.class, id);
    }

    @Override
    public void deleteAnswer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Answer> query = session.createQuery(
                "delete from Answer where id = :answerId");
        query.setParameter("answerId", id);
        query.executeUpdate();
    }
}
