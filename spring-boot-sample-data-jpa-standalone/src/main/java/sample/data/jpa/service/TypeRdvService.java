package sample.data.jpa.service;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.TypeRdv;
import sample.data.jpa.rest.ProfessionnelResource;
import sample.data.jpa.rest.TypeRdvResource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TypeRdvService {

    private TypeRdvResource typeRdvResource = new TypeRdvResource();

    public void createTypeRdvs() {
        int numOfTypeRdvs = typeRdvResource.getTypeRdvs().size();
        if (numOfTypeRdvs == 0) {
            ProfessionnelResource professionnelResource = new ProfessionnelResource();
            Professionnel professionnel = professionnelResource.getProfById(2L);
            typeRdvResource.addTypeRdv(new TypeRdv("Consultation", professionnel, 15));
            typeRdvResource.addTypeRdv(new TypeRdv("Expertise", professionnel, 30));
        }
    }

    public void listTypeRdvTest() {
        TypeRdvResource typeRdvResource = new TypeRdvResource();
        ProfessionnelResource professionnelResource = new ProfessionnelResource();
        Professionnel professionnel = professionnelResource.getProfById(2L);
        List<TypeRdv> resultList = typeRdvResource.getListTypeRdvsParProf(professionnel);
        System.out.println("\nNombre de type de rdv pour " + professionnel.getNom() + " " + professionnel.getPrenom() + ": " + resultList.size());
        for (TypeRdv next : resultList) {
            System.out.println("Type de rdv suivant : " + next);
        }
        System.out.println();
    }

    public void listTypeRdvs() {
        List<TypeRdv> resultList = typeRdvResource.getTypeRdvs();
        System.out.println("\nNombre de TypeRdvs :" + resultList.size());
        for (TypeRdv next : resultList) {
            System.out.println("TypeRdv suivant : " + next);
        }
        System.out.println();
    }
}
