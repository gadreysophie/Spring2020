package sample.data.jpa.service;

import sample.data.jpa.domain.Departement;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.rest.DepartementResource;
import sample.data.jpa.rest.ProfessionnelResource;
import java.sql.Time;

public class ProfessionnelService {
    private ProfessionnelResource professionnelResource = new ProfessionnelResource();

    public void createProfessionnels() {
        int numOfEmployees = professionnelResource.getProfs().size();
        if (numOfEmployees == 0) {
            DepartementResource departementResource = new DepartementResource();

            Departement departement = departementResource.getDepartementById(1L);
            Departement departement2 = departementResource.getDepartementById(2L);
            professionnelResource.addProfessionnel(new Professionnel("Vorc'h","Raoul","rvorch", "rvorch@univrennes.fr",
                    "rvorch",departement, Time.valueOf("10:30:00"), Time.valueOf("18:00:00"), Time.valueOf("12:30:00"),
                    Time.valueOf("13:30:00"), "1110110"));
            professionnelResource.addProfessionnel(new Professionnel("Bousse","Marc","mbousse","mbousse@univrennes.fr",
                    "mbousse", departement2, Time.valueOf("9:00:00"), Time.valueOf("17:00:00"), Time.valueOf("12:30:00"),
                    Time.valueOf("14:00:00"),"1110110"));
        }
    }
}
