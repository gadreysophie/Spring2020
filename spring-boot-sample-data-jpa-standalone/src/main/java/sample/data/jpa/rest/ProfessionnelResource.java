package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.domain.Professionnel;

@RestController("/prof")
public class ProfessionnelResource {
    @Autowired
    ProfessionnelDao professionnelDao;

    @GetMapping(path="{/profId}",produces = "application/json")
    public Professionnel getProfById(@PathVariable("profId") Long profId)  {
        return professionnelDao.searchProfessionnelById(profId);
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
        return ResponseEntity.accepted().build();
    }
}
