package web.dao;

import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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

//        System.out.println(user.getPassword());
//        System.out.println(user.getName());
//        System.out.println(user.getId());
        //user.getRoles();

        entityManager.persist(user);

    }

    @Override
    public User getUserById(Long id) {

        User user = entityManager.find(User.class, id);
        //entityManager.detach(user);
        return user;

    }

    @Override
    public User getUserByName(String name) {
//        TypedQuery q = entityManager.createQuery(
//                "SELECT u FROM User u WHERE u.name = :name", User.class);

        Query q = (Query) entityManager.createQuery(
                "select u from User u where u.name = :name");
        q.setParameter("name", name);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
                //userRepository.findByUsername(name);

    }

    @Override
    public void update(User user) {

//        System.out.println(user.getPassword());
//        System.out.println(user.getName());
//        System.out.println(user.getId());
//        user.getRoles();

        entityManager.merge(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {


        return entityManager.createQuery("select u from User as u").getResultList();
    }

    @Override
    public void del(User user) {

        entityManager.remove(entityManager.contains(user)
                                ? user
                                : entityManager.merge(user));
    }
}
