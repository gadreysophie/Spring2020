package sample.data.jpa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.data.jpa.domain.Personne;
import java.util.List;

@Transactional
public interface PersonneDao extends JpaRepository<Personne, Long> {

    /**
     * To get the list of people
     * @return a list of people
     */
    @Query("Select p From Personne p")
    List<Personne> listPersonnes() ;
}
