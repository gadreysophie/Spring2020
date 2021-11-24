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
            departementService.createFalseDepartements();

            professionnelService.createFalseProfessionnels();

            utilisateurService.createFalseUtilisateurs();

            typeRdvService.createFalseTypeRdvs();

            rdvService.createFalseRdvs();

            professionnelService.listProfessionnelsTest();
            departementService.listDepartementsTest();
            utilisateurService.listUtilisateursTest();
            rdvService.listRdvTest();
            typeRdvService.listTypeRdvsTest();
            typeRdvService.listTypeRdvParProfTest();
            rdvService.listCreneauxDispoTest();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
