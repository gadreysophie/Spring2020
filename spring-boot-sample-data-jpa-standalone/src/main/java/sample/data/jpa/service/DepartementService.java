package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.DepartementDao;
import sample.data.jpa.domain.Departement;
import java.util.List;

public class DepartementService {

    @Autowired
    DepartementDao departementDao;

    /**
     * To create department on the database
     */
    public void createFalseDepartements() {
        int numOfDepartements = departementDao.listDepartements().size();
        if (numOfDepartements == 0) {
            departementDao.save(new Departement("Master 1 - CCN"));
            departementDao.save(new Departement("Master 2 - CCNa"));
        }
    }

    /**
     * To get the list of department
     */
    public void listDepartementsTest() {
        List<Departement> resultList = departementDao.listDepartements();
        System.out.println("\nNombre de départements : " + resultList.size());
        for (Departement next : resultList) {
            System.out.println("Département suivant : " + next);
        }
        System.out.println();
    }
}
