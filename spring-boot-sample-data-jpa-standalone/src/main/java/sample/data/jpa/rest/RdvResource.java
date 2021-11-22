package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.domain.Rdv;
import sample.data.jpa.dto.RdvsParProfessionnelEtDate;

import java.util.List;

@RestController()
@RequestMapping(path = "/rdv")

public class RdvResource {
    @Autowired
    RdvDao rdvDao;

    @GetMapping(path="/{rdvId}",produces = "application/json")
    public Rdv getRdvById(@PathVariable("rdvId") Long rdvId)  {
        return rdvDao.searchRdvById(rdvId);
    }

    @GetMapping(path="/listRdv",produces = "application/json")
    public List<Rdv> getRdvs()  {
        return rdvDao.listRdvs();
    }

    @GetMapping(path="/listRdvParProfEtDate/{rdvsParProfessionnelEtDate}",produces = "application/json")
    public List<Rdv> getRdvsParProfEtDate(@PathVariable("rdvsParProfessionnelEtDate") RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate)  {
        return rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate);
    }

    // rajout liste créneaux dispo

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Rdv> addRdv(
            @RequestBody Rdv rdv) {
        rdvDao.save(rdv);
        return ResponseEntity.ok(rdv);
    }

    @DeleteMapping(path="/{rdvId}")
    public ResponseEntity<Void>  deleteRdvById(@PathVariable("rdvId") Long rdvId)  {
        rdvDao.delete(rdvDao.searchRdvById(rdvId));
        return ResponseEntity.ok().build();
    }
}
