package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.TypeRdv;
import sample.data.jpa.rest.ProfessionnelResource;
import java.util.List;

public class TypeRdvService {

    @Autowired
    TypeRdvDao typeRdvDao;

    @Autowired
    ProfessionnelDao professionnelDao;

    /**
     * To create a type of rdv on the database
     */
    public void createFalseTypeRdvs() {
        int numOfTypeRdvs = typeRdvDao.listTypeRdvs().size();
        if (numOfTypeRdvs == 0) {
            ProfessionnelResource professionnelResource = new ProfessionnelResource();
            Professionnel professionnel = professionnelResource.getProfById(2L);
            typeRdvDao.save(new TypeRdv("Consultation", professionnel, 15));
            typeRdvDao.save(new TypeRdv("Expertise", professionnel, 30));
        }
    }

    /**
     * To get the list of type of rdv by professional
     */
    public void listTypeRdvParProfTest() {

        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        List<TypeRdv> resultList = typeRdvDao.listTypeRdvsParProf(professionnel.getId());
        System.out.println("\nNombre de type de rdv pour " + professionnel.getNom() + " " + professionnel.getPrenom() + ": " + resultList.size());
        for (TypeRdv next : resultList) {
            System.out.println("Type de rdv suivant : " + next);
        }
        System.out.println();
    }

    /**
     * To get the list of type of rdv
     */
    public void listTypeRdvsTest() {
        List<TypeRdv> resultList = typeRdvDao.listTypeRdvs();
        System.out.println("\nNombre de TypeRdvs :" + resultList.size());
        for (TypeRdv next : resultList) {
            System.out.println("TypeRdv suivant : " + next);
        }
        System.out.println();
    }
}
