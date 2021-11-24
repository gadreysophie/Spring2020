package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.DepartementDao;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.domain.Departement;
import sample.data.jpa.domain.Professionnel;
import java.sql.Time;
import java.util.List;

public class ProfessionnelService {
    @Autowired
    ProfessionnelDao professionnelDao;

    @Autowired
    DepartementDao departementDao;

    /**
     * to create professionals on the database
     */
    public void createFalseProfessionnels() {
        int numOfEmployees = professionnelDao.listProfessionnels().size();
        if (numOfEmployees == 0) {

            Departement departement = departementDao.searchDepartementById(1L);
            Departement departement2 = departementDao.searchDepartementById(2L);
            professionnelDao.save(new Professionnel("Vorc'h","Raoul","rvorch", "rvorch@univrennes.fr",
                    "rvorch",departement, Time.valueOf("10:30:00"), Time.valueOf("18:00:00"), Time.valueOf("12:30:00"),
                    Time.valueOf("13:30:00"), "1110110"));
            professionnelDao.save(new Professionnel("Bousse","Marc","mbousse","mbousse@univrennes.fr",
                    "mbousse", departement2, Time.valueOf("9:00:00"), Time.valueOf("17:00:00"), Time.valueOf("12:30:00"),
                    Time.valueOf("14:00:00"),"1110110"));
        }
    }

    /**
     * to get the list of professionals
     */
    public void listProfessionnelsTest() {
        List<Professionnel> resultList = professionnelDao.listProfessionnels();
        System.out.println("\nNombre de professionnels : " + resultList.size());
        for (Professionnel next : resultList) {
            System.out.println("Professionnel suivant : " + next);
        }
        System.out.println();
    }
}
