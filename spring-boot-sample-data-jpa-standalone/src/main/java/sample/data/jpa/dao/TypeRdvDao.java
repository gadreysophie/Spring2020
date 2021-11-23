package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.*;
import java.util.List;

@Transactional
public interface TypeRdvDao extends JpaRepository<TypeRdv,Long> {

    @Query("Select t From TypeRdv t")
    List<TypeRdv> listTypeRdvs() ;

    @Query("SELECT t FROM TypeRdv t WHERE t.id =:id")
    TypeRdv searchTypeRdvById(@Param("id") Long id);

    @Query("SELECT t FROM TypeRdv t WHERE t.professionnel.id =:profId")
    List<TypeRdv> listTypeRdvsParProf(@Param("profId") Long profId);

    @Query("SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel.id =:profId")
    Integer minDureeTypeRdvByProf(@Param("profId") Long profId);
}
