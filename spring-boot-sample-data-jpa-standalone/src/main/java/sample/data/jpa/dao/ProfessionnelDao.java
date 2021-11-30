package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.Professionnel;
import java.util.List;

public interface ProfessionnelDao extends JpaRepository<Professionnel, Long> {
   /**
    * To get the list of professionals
    * @return a list of professionals
    */
   @Query("Select p From Professionnel p")
   List<Professionnel> listProfessionnels() ;

   /**
    * To get the list of professionals by name
    * @param name a last name
    * @return a list of professionals
    */
   @Query("SELECT p FROM Professionnel p WHERE p.nom LIKE CONCAT('%',:name,'%') ORDER BY p.nom")
   List<Professionnel> listProfessionnelsParNom(@Param("name") String name);

   /**
    * To search a professional by id
    * @param id an id
    * @return a professional
    */
   @Query("SELECT p FROM Professionnel p WHERE p.id =:id")
   Professionnel searchProfessionnelById(@Param("id") Long id);
}
