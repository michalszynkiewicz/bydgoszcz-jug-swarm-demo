package pl.jug.bydgoszcz.swarm.apples;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/18/17
 */
@ApplicationScoped
public class AppleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Apple save(Apple apple) {
        entityManager.persist(apple);
        return apple;
    }

    public List<Apple> getAll() {
        return entityManager
                .createQuery("SELECT a FROM Apple a", Apple.class)
                .getResultList();
    }
}
