package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.domain.TypeRdv;
import java.util.List;

@RestController()
@RequestMapping(path = "/typerdv")
public class TypeRdvResource {

    @Autowired
    TypeRdvDao typeRdvDao;

    /**
     * To search a type of rdv by id
     * @param typeRdvId the id of the type of rdv
     * @return a type of rdv
     */
    @GetMapping(path="/{typeRdvId}",produces = "application/json")
    public TypeRdv getTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        return typeRdvDao.searchTypeRdvById(typeRdvId);
    }

    /**
     * To get the minimum duration of a rdv for a professional by its id
     * @param profId the id of a professional
     * @return a duration of rdv
     */
    @GetMapping(path="/minTypeRdv/{profId}",produces = "application/json")
    public Integer getMinDureeTypeRdvByProfId(@PathVariable("profId") Long profId)  {
        return typeRdvDao.minDureeTypeRdvByProf(profId);
    }

    /**
     * To get the list of types of rdv for a professional by its id
     * @param profId the id of a professional
     * @return a list of types of rdv
     */
    @GetMapping(path="/listTypeRdv/{profId}",produces = "application/json")
    public List<TypeRdv> getListTypeRdvsParProfId(@PathVariable("profId") Long profId)  {
        return typeRdvDao.listTypeRdvsParProf(profId);
    }

    /**
     * To get the list of types of rdv
     * @return a list of types of rdv
     */
    @GetMapping(path="/listTypeRdv",produces = "application/json")
    public List<TypeRdv> getTypeRdvs()  {
        return typeRdvDao.listTypeRdvs();
    }

    /**
     * To add a type of rdv in the database
     * @param typeRdv a type of rdv
     * @return a http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> addTypeRdv(
            @RequestBody TypeRdv typeRdv) {
        typeRdvDao.save(typeRdv);
        return ResponseEntity.ok(typeRdv);
    }

    /**
     * To update a type of rdv in the database
     * @param typeRdv a type of rdv
     * @return a http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> updateTypeRdv(
            @RequestBody TypeRdv typeRdv) {
        typeRdvDao.save(typeRdv);
        return ResponseEntity.ok(typeRdv);
    }

    /**
     * To delete a type of rdv by its id in the database
     * @param typeRdvId the id of the type of rdv
     * @return a http response to get the status of the request
     */
    @DeleteMapping(path="/{typeRdvId}")
    public ResponseEntity<Void>  deleteTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        typeRdvDao.delete(typeRdvDao.searchTypeRdvById(typeRdvId));
        return ResponseEntity.ok().build();
    }
}

