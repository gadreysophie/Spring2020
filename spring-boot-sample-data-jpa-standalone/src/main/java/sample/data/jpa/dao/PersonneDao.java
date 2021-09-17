package sample.data.jpa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.data.jpa.domain.Personne;

@Transactional
public interface PersonneDao extends JpaRepository<Personne, Long> {

    @Query("Select p From Personne p")
    public void listPersonnes() ;
}
