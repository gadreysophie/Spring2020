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
     * to get the type of rdv by id
     * @param typeRdvId the id of the type of rdv
     * @return the type of rdv
     */
    @GetMapping(path="/{typeRdvId}",produces = "application/json")
    public TypeRdv getTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        return typeRdvDao.searchTypeRdvById(typeRdvId);
    }

    /**
     * to get the minimum length of a rdv for a professional
     * @param profId the professional id
     * @return the length of a rdv for the professional
     */
    @GetMapping(path="/minTypeRdv/{profId}",produces = "application/json")
    public Integer getMinDureeTypeRdvByProfId(@PathVariable("profId") Long profId)  {
        return typeRdvDao.minDureeTypeRdvByProf(profId);
    }

    /**
     * to get the list of type of rdv by professional
     * @param profId the professional id
     * @return the list of type of rdv for this professional
     */
    @GetMapping(path="/listTypeRdv/{profId}",produces = "application/json")
    public List<TypeRdv> getListTypeRdvsParProfId(@PathVariable("profId") Long profId)  {
        return typeRdvDao.listTypeRdvsParProf(profId);
    }

    /**
     * to get the list of type of rdv
     * @return the list of type of rdv
     */
    @GetMapping(path="/listTypeRdv",produces = "application/json")
    public List<TypeRdv> getTypeRdvs()  {
        return typeRdvDao.listTypeRdvs();
    }

    /**
     * to add a type of rdv on the database
     * @param typeRdv type of rdv
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> addTypeRdv(
            @RequestBody TypeRdv typeRdv) {
        typeRdvDao.save(typeRdv);
        return ResponseEntity.ok(typeRdv);
    }

    /**
     * to update a type of rdv on the database
     * @param typeRdv type of rdv
     * @return the http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> updateTypeRdv(
            @RequestBody TypeRdv typeRdv) {
        typeRdvDao.save(typeRdv);
        return ResponseEntity.ok(typeRdv);
    }

    /**
     * to delete a type of rdv by id
     * @param typeRdvId the id of the type of rdv
     * @return the http response to get the status of the request
     */
    @DeleteMapping(path="/{typeRdvId}")
    public ResponseEntity<Void>  deleteTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        typeRdvDao.delete(typeRdvDao.searchTypeRdvById(typeRdvId));
        return ResponseEntity.ok().build();
    }
}

