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
     * to get the user by the id
     * @param userId the id of the user
     * @return the user
     */
    @GetMapping(path="/{userId}",produces = "application/json")
    public Utilisateur getUserById(@PathVariable("userId") Long userId)  {
        return utilisateurDao.searchUserById(userId);
    }

    /**
     * to get the list of user from the database
     * @return the list of user
     */
    @GetMapping(path="/listUser",produces = "application/json")
    public List<Utilisateur> getUsers()  {
        return utilisateurDao.findAll();
    }

    /**
     * to add a user on the database
     * @param user the user to add
     * @return the http response to get the status of the request
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur user){
        utilisateurDao.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * to delete an user from the database
     * @param userId the id of the user
     * @return the http response to get the status of the request
     */
    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void>  deleteUserById(@PathVariable("userId") Long userId)  {
        utilisateurDao.delete(utilisateurDao.searchUserById(userId));
        return ResponseEntity.ok().build();
    }
}
