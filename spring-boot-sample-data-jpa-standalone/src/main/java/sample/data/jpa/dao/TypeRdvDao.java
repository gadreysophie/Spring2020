package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Type;
import java.util.List;
@Transactional
public interface TypeRdvDao extends JpaRepository<TypeRdv,Long> {





    @Query("Select a From TypeRdv a")
    public void listTypeRdvs() ;

    @Query("SELECT t FROM TypeRdv t WHERE t.id =:id")
    public TypeRdv searchTypeRdvById(@PathVariable("id") Long id);
    @Query("SELECT t FROM TypeRdv t WHERE t.professionnel =:prof")
    public List<TypeRdv> listTypeRdvsParProf(@PathVariable("prof") Professionnel prof);

    @Query("SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel =:prof")
    public Integer minDureeTypeRdvByProf(@PathVariable("prof") Professionnel prof);

}
