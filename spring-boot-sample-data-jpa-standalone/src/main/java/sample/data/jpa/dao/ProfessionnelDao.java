package sample.data.jpa.dao;

import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.Departement;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.User;

public interface ProfessionnelDao extends JpaRepository<User, Long> {



   @Query("Select a From Professionnel a")
    public void listProfessionnels() ;

   @Query("SELECT p FROM Professionnel p WHERE p.nom LIKE CONCAT('%',:name,'%') ORDER BY p.nom")
    public void listProfessionnelsParNom (@PathVariable("name") String name);

   @Query("SELECT p FROM Professionnel p WHERE p.id =:id")
    public Professionnel searchProfessionnelById(@PathVariable("id") Long id);

}
