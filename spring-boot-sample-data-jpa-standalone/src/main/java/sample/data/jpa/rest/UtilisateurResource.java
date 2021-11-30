package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.Utilisateur;

import java.util.List;

@RestController()
@RequestMapping(path = "/user")
public class UtilisateurResource {

    @Autowired
    UtilisateurDao utilisateurDao;

    /**
     * To search a user by id
     * @param userId the id of the user
     * @return a user
     */
    @GetMapping(path="/{userId}",produces = "application/json")
    public Utilisateur getUserById(@PathVariable("userId") Long userId)  {
        return utilisateurDao.searchUserById(userId);
    }

    /**
     * To get the list of users
     * @return a list of users
     */
    @GetMapping(path="/listUser",produces = "application/json")
    public List<Utilisateur> getUsers()  {
        return utilisateurDao.findAll();
    }

    /**
     * To add a user in the database
     * @param user a user
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur user){
        utilisateurDao.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * To update a user in the database
     * @param user a user
     * @return a http response to get the status of the request
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Utilisateur> updateUser(@RequestBody Utilisateur user){
        utilisateurDao.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * To delete a user by its id in the database
     * @param userId the id of the user
     * @return a http response to get the status of the request
     */
    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void>  deleteUserById(@PathVariable("userId") Long userId)  {
        utilisateurDao.delete(utilisateurDao.searchUserById(userId));
        return ResponseEntity.ok().build();
    }
}
