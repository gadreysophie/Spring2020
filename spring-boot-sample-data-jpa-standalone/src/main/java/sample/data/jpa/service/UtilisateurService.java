package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.Utilisateur;

import java.util.List;

public class UtilisateurService {

    @Autowired
    UtilisateurDao utilisateurDao;

    /**
     * To create false data of users in the database
     */
    public void createFalseUtilisateurs() {
        int numOfUsers = utilisateurDao.listUtilisateurs().size();
        if (numOfUsers == 0) {
            utilisateurDao.save(new Utilisateur("Gadrey","Sophie","sgadrey","sgadrey@univrennes.fr","sgadrey"));
            utilisateurDao.save(new Utilisateur("Le Chenadec","Erwann","elechenadec","elechenadec@univrennes.fr","elechenadec"));
        }
    }

    /**
     * To print the list of users
     */
    public void listUtilisateursTest() {
        List<Utilisateur> resultList = utilisateurDao.listUtilisateurs();
        System.out.println("\nNombre d'utilisateurs : " + resultList.size());
        for (Utilisateur next : resultList) {
            System.out.println("Utilisateur suivant : " + next);
        }
        System.out.println();
    }
}
