package sample.data.jpa.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.data.jpa.domain.Personne;
import sample.data.jpa.domain.User;

@Transactional
public interface PersonneDao extends JpaRepository<User, Long> {

    @Query("Select p From Personne p")
    public void listPersonnes() ;
}
