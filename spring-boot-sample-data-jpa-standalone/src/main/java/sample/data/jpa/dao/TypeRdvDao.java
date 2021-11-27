package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.*;
import java.util.List;

@Transactional
public interface TypeRdvDao extends JpaRepository<TypeRdv,Long> {
    /**
     * To get the list of types of rdv from the database
     * @return the list of types of rdv
     */
    @Query("Select t From TypeRdv t")
    List<TypeRdv> listTypeRdvs() ;

    /**
     * To search a type of rdv by id
     * @param id the id of the type of rdv
     * @return the type of rdv
     */
    @Query("SELECT t FROM TypeRdv t WHERE t.id =:id")
    TypeRdv searchTypeRdvById(@Param("id") Long id);

    /**
     * To get a list of types of rdv by professional
     * @param profId the professional id
     * @return the list of types of rdv for the professional
     */
    @Query("SELECT t FROM TypeRdv t WHERE t.professionnel.id =:profId")
    List<TypeRdv> listTypeRdvsParProf(@Param("profId") Long profId);

    /**
     * To get the minimum length of a type of rdv
     * @param profId the professional id
     * @return the minimum length of a type of rdv from a professional
     */
    @Query("SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel.id =:profId")
    Integer minDureeTypeRdvByProf(@Param("profId") Long profId);
}
