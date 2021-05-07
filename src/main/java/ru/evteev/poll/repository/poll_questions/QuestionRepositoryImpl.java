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
public class QuestionRepositoryImpl implements QuestionRepository {

    private final SessionFactory sessionFactory;

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
