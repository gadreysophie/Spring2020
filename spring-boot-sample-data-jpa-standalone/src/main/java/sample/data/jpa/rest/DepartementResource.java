package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.DepartementDao;
import sample.data.jpa.domain.Departement;
import java.util.List;

@RestController()
@RequestMapping(path = "/dept")

public class DepartementResource {

    @Autowired
    DepartementDao departementDao;

    /**
     * to get the departement searching by id
     * @param deptId the id of the departement
     * @return the department
     */
    @GetMapping(path="/{deptId}",produces = "application/json")
    public Departement getDepartementById(@PathVariable("deptId") Long deptId)  {
        return departementDao.searchDepartementById(deptId);
    }

    /**
     * to add a department in the database
     * @param dept the department to add in the database
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Departement> addDepartement(
           @RequestBody Departement dept) {
        departementDao.save(dept);
        return ResponseEntity.ok(dept);
    }

    /**
     * to get the list of the department that are in the database
     * @return a list of department
     */
    @GetMapping(path="/listDept",produces = "application/json")
    public List<Departement> getDepts()  {
        return departementDao.listDepartements();
    }

    /**
     * to delete a department from the database by the id
     * @param deptId the department id
     * @return the http response to get the status of the request
     */
    @DeleteMapping(path="/{deptId}")
    public ResponseEntity<Void> deleteDepartById(@PathVariable("deptId") Long deptId)  {
        departementDao.delete(departementDao.searchDepartementById(deptId));
        return ResponseEntity.ok().build();
    }
}
