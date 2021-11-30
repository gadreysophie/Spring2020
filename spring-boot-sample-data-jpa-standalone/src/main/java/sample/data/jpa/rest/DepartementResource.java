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
     * To search a department by id
     * @param deptId the id of a departement
     * @return a department
     */
    @GetMapping(path="/{deptId}",produces = "application/json")
    public Departement getDepartementById(@PathVariable("deptId") Long deptId)  {
        return departementDao.searchDepartementById(deptId);
    }

    /**
     * To add a department in the database
     * @param dept a department
     * @return a http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Departement> addDepartement(
            @RequestBody Departement dept) {
        departementDao.save(dept);
        return ResponseEntity.ok(dept);
    }

    /**
     * To update a department in the database
     * @param dept a department
     * @return a http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Departement> updateDepartement(
            @RequestBody Departement dept) {
        departementDao.save(dept);
        return ResponseEntity.ok(dept);
    }

    /**
     * To get the list of departments from the database
     * @return a list of departments
     */
    @GetMapping(path="/listDept",produces = "application/json")
    public List<Departement> getDepts()  {
        return departementDao.listDepartements();
    }

    /**
     * To delete a department in the database by an id
     * @param deptId the id of a department
     * @return a http response to get the status of the request
     */
    @DeleteMapping(path="/{deptId}")
    public ResponseEntity<Void> deleteDepartById(@PathVariable("deptId") Long deptId)  {
        departementDao.delete(departementDao.searchDepartementById(deptId));
        return ResponseEntity.ok().build();
    }
}
