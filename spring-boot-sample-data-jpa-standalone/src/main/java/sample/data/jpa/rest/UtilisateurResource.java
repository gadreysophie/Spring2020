package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.Utilisateur;

@RestController("/user")
public class UtilisateurResource {
    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping(path="{/userId}",produces = "application/json")
    public Utilisateur getUserById(@PathVariable("userId") Long userId)  {
        return utilisateurDao.searchUserById(userId);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur user){
        utilisateurDao.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void>  deleteUserById(@PathVariable("userId") Long userId)  {
        utilisateurDao.delete(utilisateurDao.searchUserById(userId));
        return ResponseEntity.accepted().build();
    }
}
