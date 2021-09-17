package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.domain.Rdv;

@RestController("/rdv")
public class RdvResource {

    @Autowired
    RdvDao rdvDao;

    @GetMapping(path="{/rdvId}",produces = "application/json")
    public Rdv getRdvById(@PathVariable("rdvId") Long rdvId)  {
        return rdvDao.searchRdvById(rdvId);
    }

    @PostMapping(consumes = "application/json")
    public Response addRdv(
            @Parameter(description = "Rdv object that needs to be added to the store", required = true) Rdv rdv) {
        rdvDao.addRdv(rdv);
        return Response.ok().entity(rdv).build();
    }

    @DeleteMapping(path="/{rdvId}")
    public Response deleteRdvById(@PathVariable("rdvId") Long rdvId)  {
        rdvDao.deleteRdvById(rdvId);
        return Response.ok().build();
    }


}
