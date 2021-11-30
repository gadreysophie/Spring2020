package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.domain.Rdv;
import sample.data.jpa.dto.CreneauxDispoParProfEtDateEtTypeRdvDto;
import sample.data.jpa.dto.RdvsParProfessionnelEtDateDto;
import sample.data.jpa.service.RdvService;
import java.util.List;

@RestController()
@RequestMapping(path = "/rdv")
public class RdvResource {

    @Autowired
    RdvDao rdvDao;

    @Autowired
    TypeRdvDao typeRdvDao;

    /**
     * To search a rdv by id
     * @param rdvId the id of a rdv
     * @return a rdv
     */
    @GetMapping(path="/{rdvId}",produces = "application/json")
    public Rdv getRdvById(@PathVariable("rdvId") Long rdvId)  {
        return rdvDao.searchRdvById(rdvId);
    }

    /**
     * To get the list of rdvs from the database
     * @return a list of rdvs
     */
    @GetMapping(path="/listRdv",produces = "application/json")
    public List<Rdv> getRdvs()  {
        return rdvDao.listRdvs();
    }

    /**
     * To get the list of rdvs by date and professional from the database
     * @param rdvsParProfessionnelEtDate a date and a professional
     * @return a list of rdvs
     */
    @GetMapping(path="/listRdvParProfEtDate/", produces = "application/json")
    public List<Rdv> getRdvsParProfEtDate(@RequestBody RdvsParProfessionnelEtDateDto rdvsParProfessionnelEtDate)  {
        return rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate.getProfessionnel().getId(), rdvsParProfessionnelEtDate.getDate(), rdvsParProfessionnelEtDate.getDate2());
    }

    @GetMapping(path = "/creneauxDispoParProfEtDateEtTypeRdv/", produces = "application/json")
    public List<Rdv> getCreneauxDispoParProfEtDateEtTypeRdv(@RequestBody CreneauxDispoParProfEtDateEtTypeRdvDto creneauxDispoParProfEtDateEtTypeRdv){
        return RdvService.listCreneauxDispo(creneauxDispoParProfEtDateEtTypeRdv, rdvDao, typeRdvDao);
    }

    /**
     * To add a rdv in the database
     * @param rdv a rdv
     * @return a http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Rdv> addRdv(
            @RequestBody Rdv rdv) {
        rdvDao.save(rdv);
        return ResponseEntity.ok(rdv);
    }

    /**
     * To update a rdv in the database
     * @param rdv the rdv
     * @return a http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Rdv> updateRdv(
            @RequestBody Rdv rdv) {
        rdvDao.save(rdv);
        return ResponseEntity.ok(rdv);
    }

    /**
     * To delete a rdv in the database by id
     * @param rdvId the id of a rdv
     * @return a http response to get the status of the request
     */
    @DeleteMapping(path="/{rdvId}")
    public ResponseEntity<Void>  deleteRdvById(@PathVariable("rdvId") Long rdvId)  {
        rdvDao.delete(rdvDao.searchRdvById(rdvId));
        return ResponseEntity.ok().build();
    }
}
