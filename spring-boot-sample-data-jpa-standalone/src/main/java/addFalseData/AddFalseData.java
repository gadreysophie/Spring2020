package addFalseData;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import sample.data.jpa.service.*;

import java.text.ParseException;

@SpringBootApplication
public class AddFalseData {

    public static void main(String[] args) {
        ProfessionnelService professionnelService = new ProfessionnelService();
        DepartementService departementService = new DepartementService();
        UtilisateurService utilisateurService = new UtilisateurService();
        RdvService rdvService = new RdvService();
        TypeRdvService typeRdvService = new TypeRdvService();

        try {
            departementService.createDepartements();

            professionnelService.createProfessionnels();

            utilisateurService.createUtilisateurs();

            typeRdvService.createTypeRdvs();

            rdvService.createRdvs();

            //professionnelService.listProfessionnelsParNom("Prof");
            professionnelService.listProfessionnels();
            departementService.listDepartements();
            utilisateurService.printListUtilisateurs();
            rdvService.listRdvTest();
            typeRdvService.listTypeRdvs();
            typeRdvService.listTypeRdvTest();
            rdvService.testListCreneauxDispo();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
