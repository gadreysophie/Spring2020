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

    /**
     * to get a professional by the id
     * @param profId the professional id
     * @return the professional
     */
    @GetMapping(path="/{profId}",produces = "application/json")
    public Professionnel getProfById(@PathVariable("profId") Long profId)  {
        return professionnelDao.searchProfessionnelById(profId);
    }

    /**
     * to get the list of professional on the database
     * @return a list of the professional on the database
     */
    @GetMapping(path="/listProf",produces = "application/json")
    public List<Professionnel> getProfs()  {
        return professionnelDao.listProfessionnels();
    }

    /**
     * to get a list of professionals by the name
     * @param nomProf the name of professionals
     * @return a list of professionals by name
     */
    @GetMapping(path="/listProf/{nomProf}",produces = "application/json")
    public List<Professionnel> getProfsByName(@PathVariable("nomProf") String nomProf)  {
        return professionnelDao.listProfessionnelsParNom(nomProf);
    }

    /**
     * to add a professional to the database
     * @param prof the professional to add
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Professionnel> addProfessionnel(
            @RequestBody Professionnel prof) {
        professionnelDao.save(prof);
        return ResponseEntity.ok(prof);
    }

    /**
     * to update a professional to the database
     * @param prof the professional to add
     * @return the http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Professionnel> updateProfessionnel(
            @RequestBody Professionnel prof) {
        professionnelDao.save(prof);
        return ResponseEntity.ok(prof);
    }

    /**
     * to delete a professional from the database
     * @param profId the professional id
     * @return the http response to get the status of the request
     */
    @DeleteMapping(path="/{profId}")
    public ResponseEntity<Void> deleteProfById(@PathVariable("profId") Long profId)  {
        professionnelDao.delete(professionnelDao.searchProfessionnelById(profId));
        return ResponseEntity.ok().build();
    }
}
