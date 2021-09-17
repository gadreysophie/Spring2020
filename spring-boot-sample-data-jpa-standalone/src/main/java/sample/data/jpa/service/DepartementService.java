package sample.data.jpa.service;

import sample.data.jpa.dao.EntityManagerHelper;
import sample.data.jpa.domain.Departement;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DepartementService {
    private final EntityManager manager = EntityManagerHelper.getEntityManager();

    public void createDepartements() {
        int numOfDepartements = manager.createQuery("Select a From Departement a", Departement.class).getResultList().size();
        if (numOfDepartements == 0) {
            EntityTransaction tx = manager.getTransaction();
            tx.begin();
            manager.persist(new Departement("Master 1 - CCN"));
            manager.persist(new Departement("Master 2 - CCNa"));
            tx.commit();
        }
    }

    public void listDepartements() {
        List<Departement> resultList = manager.createQuery("Select a From Departement a", Departement.class).getResultList();
        System.out.println("\nNombre de départements : " + resultList.size());
        for (Departement next : resultList) {
            System.out.println("Département suivant : " + next);
        }
        System.out.println();
    }
}
