package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.domain.Professionnel;
import java.util.List;

@RestController
@RequestMapping(path = "/prof")
public class ProfessionnelResource {
    @Autowired
    ProfessionnelDao professionnelDao;

    @GetMapping(path="/{profId}",produces = "application/json")
    public Professionnel getProfById(@PathVariable("profId") Long profId)  {
        return professionnelDao.searchProfessionnelById(profId);
    }

    @GetMapping(path="/listProf",produces = "application/json")
    public List<Professionnel> getProfs()  {
        return professionnelDao.listProfessionnels();
    }

    @GetMapping(path="/listProf/{nomProf}",produces = "application/json")
    public List<Professionnel> getProfsByName(@PathVariable("nomProf") String nomProf)  {
        return professionnelDao.listProfessionnelsParNom(nomProf);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Professionnel> addProfessionnel(
         @RequestBody Professionnel prof) {
        professionnelDao.save(prof);
        return ResponseEntity.ok(prof);
    }

    @DeleteMapping(path="/{profId}")
    public ResponseEntity<Void> deleteProfById(@PathVariable("profId") Long profId)  {
        professionnelDao.delete(professionnelDao.searchProfessionnelById(profId));
        return ResponseEntity.ok().build();
    }
}
