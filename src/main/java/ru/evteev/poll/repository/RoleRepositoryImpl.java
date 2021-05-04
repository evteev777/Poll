package ru.evteev.poll.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.evteev.poll.entity.Role;

import java.util.List;

@AllArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final SessionFactory sessionFactory;

    @Override

    public List<Role> getAllRoles() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> RoleList = session.createQuery("from Role order by id", Role.class)
                .getResultList();
        return RoleList;
    }

    @Override
    public void createOrUpdateRole(Role Role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Role);
    }

    @Override
    public Role getRole(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

    @Override
    public void deleteRole(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery(
                "delete from Role where id = :RoleId");
        query.setParameter("RoleId", id);
        query.executeUpdate();
    }
}
