package sample.data.jpa.dao;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.*;

@Transactional
public interface RdvDao extends JpaRepository<Rdv,Long> {
    @Query("Select r From Rdv r")
    List<Rdv> listRdvs();

    @Query("SELECT r FROM Rdv r WHERE r.id =:id")
    Rdv searchRdvById(@PathVariable("id") Long id);
}
