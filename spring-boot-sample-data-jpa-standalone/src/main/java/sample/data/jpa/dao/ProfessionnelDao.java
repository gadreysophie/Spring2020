package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.Professionnel;
import java.util.List;

public interface ProfessionnelDao extends JpaRepository<Professionnel, Long> {
   /**
    * To get the list of professionals
    * @return the list of professionals from the database
    */
   @Query("Select p From Professionnel p")
   List<Professionnel> listProfessionnels() ;

   /**
    * To get the list of professional by the name
    * @param name the name of professionals
    * @return the list of professionals by name
    */
   @Query("SELECT p FROM Professionnel p WHERE p.nom LIKE CONCAT('%',:name,'%') ORDER BY p.nom")
   List<Professionnel> listProfessionnelsParNom(@Param("name") String name);

   /**
    * To search a professional from the database by the id
    * @param id the professional id
    * @return the professional
    */
   @Query("SELECT p FROM Professionnel p WHERE p.id =:id")
   Professionnel searchProfessionnelById(@Param("id") Long id);
}
