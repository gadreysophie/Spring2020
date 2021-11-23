package sample.data.jpa.service;

import sample.data.jpa.domain.Departement;
import sample.data.jpa.rest.DepartementResource;
import java.util.List;

public class DepartementService {

    private DepartementResource departementResource = new DepartementResource();

    /**
     * to create department on the database
     */
    public void createDepartements() {
        int numOfDepartements = departementResource.getDepts().size();
        if (numOfDepartements == 0) {
            departementResource.addDepartement(new Departement("Master 1 - CCN"));
            departementResource.addDepartement(new Departement("Master 2 - CCNa"));
        }
    }

    /**
     * to get the list of department
     */
    public void listDepartements() {
        List<Departement> resultList = departementResource.getDepts();
        System.out.println("\nNombre de départements : " + resultList.size());
        for (Departement next : resultList) {
            System.out.println("Département suivant : " + next);
        }
        System.out.println();
    }
}
