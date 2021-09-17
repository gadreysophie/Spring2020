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

    @GetMapping(path="/{typeRdvId}",produces = "application/json")
    public TypeRdv getTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        return typeRdvDao.searchTypeRdvById(typeRdvId);
    }

    @GetMapping(path="/minTypeRdv/{prof}",produces = "application/json")
    public Integer getMinDureeTypeRdvByProf(@PathVariable("prof") Professionnel professionnel)  {
        return typeRdvDao.minDureeTypeRdvByProf(professionnel);
    }

    @GetMapping(path="/listTypeRdv/{prof}",produces = "application/json")
    public List<TypeRdv> getListTypeRdvsParProf(@PathVariable("prof") Professionnel professionnel)  {
        return typeRdvDao.listTypeRdvsParProf(professionnel);
    }

    @GetMapping(path="/listTypeRdv",produces = "application/json")
    public List<TypeRdv> getTypeRdvs()  {
        return typeRdvDao.listTypeRdvs();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TypeRdv> addTypeRdv(
            @RequestBody TypeRdv typeRdv) {
        typeRdvDao.save(typeRdv);
        return ResponseEntity.ok(typeRdv);
    }

    @DeleteMapping(path="/{typeRdvId}")
    public ResponseEntity<Void>  deleteTypeRdvById(@PathVariable("typeRdvId") Long typeRdvId)  {
        typeRdvDao.delete(typeRdvDao.searchTypeRdvById(typeRdvId));
        return ResponseEntity.accepted().build();
    }
}

