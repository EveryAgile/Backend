package EveryEgile.jaeyeon.entity.emf;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

@PersistenceContext
public class UniqueEntityManager {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    public static EntityManager em = entityManagerFactory.createEntityManager();

}