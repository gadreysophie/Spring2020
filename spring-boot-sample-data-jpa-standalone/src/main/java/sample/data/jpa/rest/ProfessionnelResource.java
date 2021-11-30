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
     * To search a professional by id
     * @param profId the id of a professional
     * @return a professional
     */
    @GetMapping(path="/{profId}",produces = "application/json")
    public Professionnel getProfById(@PathVariable("profId") Long profId)  {
        return professionnelDao.searchProfessionnelById(profId);
    }

    /**
     * To get the list of professionals from the database
     * @return a list of professionals
     */
    @GetMapping(path="/listProf",produces = "application/json")
    public List<Professionnel> getProfs()  {
        return professionnelDao.listProfessionnels();
    }

    /**
     * To get the list of professionals from the database by name
     * @param nomProf the name of a professional
     * @return a list of Professionnel
     */
    @GetMapping(path="/listProf/{nomProf}",produces = "application/json")
    public List<Professionnel> getProfsByName(@PathVariable("nomProf") String nomProf)  {
        return professionnelDao.listProfessionnelsParNom(nomProf);
    }

    /**
     * To add a professional in the database
     * @param prof a professional
     * @return a http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Professionnel> addProfessionnel(
            @RequestBody Professionnel prof) {
        professionnelDao.save(prof);
        return ResponseEntity.ok(prof);
    }

    /**
     * To update a professional in the database
     * @param prof a professional
     * @return a http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Professionnel> updateProfessionnel(
            @RequestBody Professionnel prof) {
        professionnelDao.save(prof);
        return ResponseEntity.ok(prof);
    }

    /**
     * To delete a professional in the database by an id
     * @param profId the id of a professional
     * @return a http response to get the status of the request
     */
    @DeleteMapping(path="/{profId}")
    public ResponseEntity<Void> deleteProfById(@PathVariable("profId") Long profId)  {
        professionnelDao.delete(professionnelDao.searchProfessionnelById(profId));
        return ResponseEntity.ok().build();
    }
}
