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
     * To get the list of types of rdv
     * @return a list of types of rdv
     */
    @Query("Select t From TypeRdv t")
    List<TypeRdv> listTypeRdvs() ;

    /**
     * To search a type of rdv by id
     * @param id an id
     * @return a type of rdv
     */
    @Query("SELECT t FROM TypeRdv t WHERE t.id =:id")
    TypeRdv searchTypeRdvById(@Param("id") Long id);

    /**
     * To get the list of types of rdv by professional
     * @param profId the id of a professional
     * @return a list of types of rdv
     */
    @Query("SELECT t FROM TypeRdv t WHERE t.professionnel.id =:profId")
    List<TypeRdv> listTypeRdvsParProf(@Param("profId") Long profId);

    /**
     * To get the minimum duration of a type of rdv for a professional
     * @param profId the id of a professional
     * @return the minimum duration
     */
    @Query("SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel.id =:profId")
    Integer minDureeTypeRdvByProf(@Param("profId") Long profId);
}
