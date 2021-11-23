package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.domain.Professionnel;
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
     * to get the minimum length of the rdv of the professional
     * @param professionnel the professional
     * @return the length of the type of rdv
     */
    @GetMapping(path="/minTypeRdv/{prof}",produces = "application/json")
    public Integer getMinDureeTypeRdvByProf(@PathVariable("prof") Professionnel professionnel)  {
        return typeRdvDao.minDureeTypeRdvByProf(professionnel);
    }

    /**
     * to get the list of type of rdv by professional
     * @param professionnel the professional
     * @return the list of type of rdv by professional
     */
    @GetMapping(path="/listTypeRdv/{prof}",produces = "application/json")
    public List<TypeRdv> getListTypeRdvsParProf(@PathVariable("prof") Professionnel professionnel)  {
        return typeRdvDao.listTypeRdvsParProf(professionnel);
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
     * @param typeRdv
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> addTypeRdv(
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

