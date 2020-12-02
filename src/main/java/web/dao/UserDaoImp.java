package web.dao;

import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

//    private final EntityManagerFactory entityFactory;
//
//    @Autowired
//    public UserDaoImp(EntityManagerFactory entityFactory) {
//        this.entityFactory = entityFactory;
//    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        //EntityManager entityManager = entityFactory.createEntityManager();
        //entityManager.getTransaction().begin();
        entityManager.persist(user);
        //entityManager.getTransaction().commit();

        //sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserById(Long id) {
        //EntityManager entityManager = entityFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        //entityManager.detach(user);
        return user;

        //return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User user) {
        //EntityManager entityManager = entityFactory.createEntityManager();

        //entityManager.getTransaction().begin();
        entityManager.merge(user);
        //entityManager.getTransaction().commit();

        //sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        //EntityManager entityManager = entityFactory.createEntityManager();

        return entityManager.createQuery("select u from User as u").getResultList();
    }

    @Override
    public void del(User user) {
        //EntityManager entityManager = entityFactory.createEntityManager();

        //entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(user)
                                ? user
                                : entityManager.merge(user));
        //entityManager.getTransaction().commit();

    }
}
