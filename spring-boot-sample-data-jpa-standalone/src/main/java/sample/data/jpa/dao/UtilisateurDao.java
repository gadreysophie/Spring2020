package sample.data.jpa.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.Utilisateur;

@Transactional
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

    @Query("Select u From Utilisateur u")
    List<Utilisateur> listUtilisateurs();

    @Query("SELECT u FROM Utilisateur u WHERE u.id =:id")
    Utilisateur searchUserById(@Param("id") Long id);
}
