package sample.data.jpa.dao;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.*;

@Transactional
public interface RdvDao extends JpaRepository<Rdv,Long> {
    /**
     * To get the list of rdv from the database
     * @return the list of rdv
     */
    @Query("Select r From Rdv r")
    List<Rdv> listRdvs();

    /**
     * To search a rdv by the id
     * @param id the id of the rdv
     * @return the rdv
     */
    @Query("SELECT r FROM Rdv r WHERE r.id =:id")
    Rdv searchRdvById(@Param("id") Long id);

    /**
     * To get a list of rdv by professional and date
     * @param profId the professional id
     * @param dateDeb the beginning date
     * @param dateFin the end date
     * @return the list of rdv by date and professional
     */
    @Query("SELECT r FROM Rdv r WHERE r.professionnel.id=:profId AND  r.dateDebut >=:dateDeb AND r.dateFin <=:dateFin")
    List<Rdv> rdvsParProfessionnelEtDate(@Param("profId") Long profId, @Param("dateDeb") Date dateDeb, @Param("dateFin") Date dateFin);
}
