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

    @Query("SELECT t FROM TypeRdv t WHERE t.professionnel =:prof")
    List<TypeRdv> listTypeRdvsParProf(@Param("prof") Professionnel prof);

    @Query("SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel =:prof")
    Integer minDureeTypeRdvByProf(@Param("prof") Professionnel prof);
}
