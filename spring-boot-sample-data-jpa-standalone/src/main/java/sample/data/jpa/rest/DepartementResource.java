package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.DepartementDao;
import sample.data.jpa.domain.Departement;
import sample.data.jpa.domain.Rdv;

@RestController("/dept")
public class DepartementResource {

    @Autowired
    DepartementDao departementDao;

    @GetMapping(path="{/deptId}",produces = "application/json")
    public Departement getDepartementById(@PathVariable("deptId") Long deptId)  {
        return departementDao.searchDepartementById(deptId);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Departement> addDepartement(
           @RequestBody Departement dept) {
        departementDao.addDepartement(dept);
        return ResponseEntity.ok(dept);

    }

    @DeleteMapping(path="/{deptId}")
    public ResponseEntity<Void> deleteDepartById(@PathVariable("deptId") Long deptId)  {
        departementDao.deleteDepartById(deptId);
        return ResponseEntity.accepted().build();
    }
}
