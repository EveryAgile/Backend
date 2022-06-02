package org.everyagile.everyagile.everyagile.entity.emf;

import javax.persistence.*;

@PersistenceContext
public class UniqueEntityManager {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    public static EntityManager em = entityManagerFactory.createEntityManager();

}