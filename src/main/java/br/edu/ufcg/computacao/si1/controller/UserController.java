package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by nicacio on 13/03/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection> getUsuarios() {
        return new ResponseEntity<Collection>(usuarioService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/balance/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Double> getUserBalance (@PathVariable Long userID) {
        return new ResponseEntity<Double>(usuarioService.getById(userID).getCredit(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/qualificate/{userToQualifyID}/{alertID}/{ratingValue}", method = RequestMethod.POST)
    public ResponseEntity qualificate(@PathVariable Long userToQualifyID, @PathVariable Long alertID, @PathVariable Integer ratingValue){
        User resp = usuarioService.qualifyUserById(userToQualifyID,alertID , ratingValue);
        if (resp == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/qualificate/{userID}")
    public ResponseEntity getUserAvarageRatingValue(@PathVariable Long userID) {
        User u = usuarioService.getById(userID);
        if (u == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(u.avarageRating(), HttpStatus.OK);
    }

}
