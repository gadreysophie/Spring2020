package sample.data.jpa.service;

import sample.data.jpa.rest.*;

import java.text.ParseException;

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

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //professionnelService.listProfessionnelsParNom("Prof");
        professionnelService.listProfessionnels();
        departementService.listDepartements();
        utilisateurService.printListUtilisateurs();
        try {
            rdvService.listRdvTest();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        typeRdvService.listTypeRdvs();

        try {
            rdvService.listRdvTest();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        typeRdvService.listTypeRdvTest();
        try {
            rdvService.testListCreneauxDispo();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
