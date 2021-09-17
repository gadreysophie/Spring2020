package sample.data.jpa.service;

import sample.data.jpa.dao.EntityManagerHelper;
import sample.data.jpa.domain.Personne;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonneService {

    private final EntityManager manager = EntityManagerHelper.getEntityManager();

    public void listPersonnes() {
        List<Personne> resultList = manager.createQuery("Select p From Personne p", Personne.class).getResultList();
        System.out.println("\nNombre de personnes " + resultList.size());
        for (Personne next : resultList) {
            System.out.println("Personne suivante : " + next);
        }
        System.out.println();
    }
}
