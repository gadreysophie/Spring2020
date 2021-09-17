package sample.data.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Utilisateur;

@Transactional
public interface UtilisateurDao extends JpaRepository<User, Long> {

    @Query("Select a From Utilisateur a")
    public List<Utilisateur> listUtilisateurs();

    @Query("SELECT u FROM Utilisateur u WHERE u.id =:id")
    public Utilisateur searchUserById(Long id);

}
