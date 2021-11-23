package sample.data.jpa.dao;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.*;
import sample.data.jpa.dto.RdvsParProfessionnelEtDate;

@Transactional
public interface RdvDao extends JpaRepository<Rdv,Long> {

    @Query("Select r From Rdv r")
    List<Rdv> listRdvs();

    @Query("SELECT r FROM Rdv r WHERE r.id =:id")
    Rdv searchRdvById(@Param("id") Long id);

    @Query("SELECT r FROM Rdv r WHERE r.professionnel=?1 AND  r.dateDebut >=?2 AND r.dateFin <=?3")
    List<Rdv> rdvsParProfessionnelEtDate(@Param("dto") RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate );
}
