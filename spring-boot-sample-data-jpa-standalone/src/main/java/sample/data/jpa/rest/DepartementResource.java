package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.DepartementDao;
import sample.data.jpa.domain.Departement;

@RestController("/dept")
public class DepartementResource {

    @Autowired
    DepartementDao departementDao;

    @GetMapping(path="{/deptId}",produces = "application/json")
    public Departement getDepartementById(@PathVariable("deptId") Long deptId)  {
        return departementDao.searchDepartementById(deptId);
    }

    @PostMapping(consumes = "application/json")
    public Response addDepartement(
            @Param(description = "Departement object that needs to be added to the store", required = true) Departement dept) {
        departementDao.addDepartement(dept);
        return Response.ok().entity(dept).build();

    }

    @DeleteMapping(path="/{deptId}")
    public Response deleteDepartById(@PathVariable("deptId") Long deptId)  {
        departementDao.deleteDepartById(deptId);
        return Response.ok().build();
    }
}
