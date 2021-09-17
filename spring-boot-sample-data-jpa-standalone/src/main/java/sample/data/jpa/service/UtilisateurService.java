package sample.data.jpa.service;

import sample.data.jpa.domain.Utilisateur;
import sample.data.jpa.rest.UtilisateurResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UtilisateurService {

    private UtilisateurResource utilisateurResource = new UtilisateurResource();

    public void createUtilisateurs() {
        int numOfUsers = utilisateurResource.getUsers().size();
        if (numOfUsers == 0) {

            utilisateurResource.addUser(new Utilisateur("Gadrey","Sophie","sgadrey","sgadrey@univrennes.fr","sgadrey"));
            utilisateurResource.addUser(new Utilisateur("Le Chenadec","Erwann","elechenadec","elechenadec@univrennes.fr","elechenadec"));
        }
    }

    public void printListUtilisateurs() {
        List<Utilisateur> resultList = utilisateurResource.getUsers();
        System.out.println("\nNombre d'utilisateurs : " + resultList.size());
        for (Utilisateur next : resultList) {
            System.out.println("Utilisateur suivant : " + next);
        }
        System.out.println();
    }
}
