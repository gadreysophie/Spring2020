package sample.data.jpa.dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.*;
import sample.data.jpa.service.RdvService;

@Transactional
public interface RdvDao extends JpaRepository<Rdv,Long> {


    @Query("Select a From Rdv a")
    public void listRdvs();

    @Query("SELECT r FROM Rdv r WHERE r.id =:id")
    public Rdv searchRdvById(@PathVariable("id") Long id);

    @Query("SELECT r FROM Rdv r WHERE r.professionnel=:prof AND  r.dateDebut >=:date AND r.dateFin <=: date2")
    public List<Rdv> rdvsParProfessionnelEtDate(@PathVariable("prof") Professionnel prof, @PathVariable("date") Date date);

}
