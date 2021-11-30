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
     * To get the list of rdvs
     * @return a list of rdvs
     */
    @Query("Select r From Rdv r")
    List<Rdv> listRdvs();

    /**
     * To search a rdv by id
     * @param id an id
     * @return a rdv
     */
    @Query("SELECT r FROM Rdv r WHERE r.id =:id")
    Rdv searchRdvById(@Param("id") Long id);

    /**
     * To get the list of rdvs by professional and date
     * @param profId an id
     * @param dateDeb the start date of the appointment
     * @param dateFin the end date of the appointment
     * @return a list of rdvs
     */
    @Query("SELECT r FROM Rdv r WHERE r.professionnel.id=:profId AND  r.dateDebut >=:dateDeb AND r.dateFin <=:dateFin")
    List<Rdv> rdvsParProfessionnelEtDate(@Param("profId") Long profId, @Param("dateDeb") Date dateDeb, @Param("dateFin") Date dateFin);
}
