package sample.data.jpa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.Utilisateur;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RestController
public class UserResource {

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping(path="{/userId}",produces = "application/json")
    public Utilisateur getUserById(@PathVariable("userId") Long userId)  {
       // UtilisateurDao utilisateurDao = new UtilisateurDao();
        return utilisateurDao.searchUserById(userId);
    }


    @PostMapping(consumes = "application/json")
    public addUser(@PathVariable("user") Utilisateur user){
        return utilisateurDao.save(user);
    }

    @DeleteMapping(path="/{userId}")
    public deleteUserById(@PathVariable("userId") Long userId)  {

        return  utilisateurDao.deleteUserById(userId);;
    }

}
