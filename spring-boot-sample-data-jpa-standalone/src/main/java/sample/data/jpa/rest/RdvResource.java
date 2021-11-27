package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.domain.Rdv;
import sample.data.jpa.dto.CreneauxDispoParProfEtDateEtTypeRdv;
import sample.data.jpa.dto.RdvsParProfessionnelEtDate;
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
     * to get the rdv by the id
     * @param rdvId the id of the rdv
     * @return the rdv
     */
    @GetMapping(path="/{rdvId}",produces = "application/json")
    public Rdv getRdvById(@PathVariable("rdvId") Long rdvId)  {
        return rdvDao.searchRdvById(rdvId);
    }

    /**
     * to get the list of rdv from the database
     * @return the list of rdv
     */
    @GetMapping(path="/listRdv",produces = "application/json")
    public List<Rdv> getRdvs()  {
        return rdvDao.listRdvs();
    }

    /**
     * to get a rdv by the date and the professional
     * @param rdvsParProfessionnelEtDate the date and professional
     * @return a list of rdv by date and professional
     */
    @GetMapping(path="/listRdvParProfEtDate/", produces = "application/json")
    public List<Rdv> getRdvsParProfEtDate(@RequestBody RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate)  {
        return rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate.getProfessionnel().getId(), rdvsParProfessionnelEtDate.getDate(), rdvsParProfessionnelEtDate.getDate2());
    }


    @GetMapping(path = "/creneauxDispoParProfEtDateEtTypeRdv/", produces = "application/json")
    public List<Rdv> getCreneauxDispoParProfEtDateEtTypeRdv(@RequestBody CreneauxDispoParProfEtDateEtTypeRdv creneauxDispoParProfEtDateEtTypeRdv){
        return RdvService.listCreneauxDispo(creneauxDispoParProfEtDateEtTypeRdv, rdvDao, typeRdvDao);
    }




    /**
     * to add a rdv on the database
     * @param rdv the rdv
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Rdv> addRdv(
            @RequestBody Rdv rdv) {
        rdvDao.save(rdv);
        return ResponseEntity.ok(rdv);
    }


    /**
     * to update a rdv on the database
     * @param rdv the rdv
     * @return the http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Rdv> updateRdv(
            @RequestBody Rdv rdv) {
        rdvDao.save(rdv);
        return ResponseEntity.ok(rdv);
    }

    /**
     * to delete a rdv by the id
     * @param rdvId the id of the rdv
     * @return the http response to get the status of the request
     */
    @DeleteMapping(path="/{rdvId}")
    public ResponseEntity<Void>  deleteRdvById(@PathVariable("rdvId") Long rdvId)  {
        rdvDao.delete(rdvDao.searchRdvById(rdvId));
        return ResponseEntity.ok().build();
    }
}
