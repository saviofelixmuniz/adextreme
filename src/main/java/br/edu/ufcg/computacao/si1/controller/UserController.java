package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.comparators.ads.FactoryAdCompare;
import br.edu.ufcg.computacao.si1.model.comparators.users.FactoryUserCompare;
import br.edu.ufcg.computacao.si1.model.comparators.users.UserComparatorEnum;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by nicacio on 13/03/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection> getUsuarios() {
        Collection<User> users = usuarioService.getAll();
        Collections.sort((List) users, FactoryUserCompare.getComparator(UserComparatorEnum.AVERAGE_RATING));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/balance/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Double> getUserBalance (@PathVariable Long userID) {
        User user = usuarioService.getById(userID);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Double>(user.getCredit(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/qualificate/{userToQualifyID}/{alertID}/{ratingValue}/{qualifierID}", method = RequestMethod.POST)
    public ResponseEntity qualificate(@PathVariable Long userToQualifyID, @PathVariable Long alertID, @PathVariable Integer ratingValue, @PathVariable Long qualifierID){
        User userQualified = usuarioService.qualifyUserById(userToQualifyID,alertID , ratingValue, qualifierID);

        if (userQualified == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        User qualifier = usuarioService.getById(qualifierID);
        return new ResponseEntity(qualifier, HttpStatus.OK);
    }
    @RequestMapping(value = "/user/single/{userID}", method = RequestMethod.GET)

    public ResponseEntity<User> getUser (@PathVariable Long userID) {
        User user = usuarioService.getById(userID);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/qualificate/{userID}")
    public ResponseEntity getUserAvarageRatingValue(@PathVariable Long userID) {
        User user = usuarioService.getById(userID);
        if (user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(user.calculateAverageRating(), HttpStatus.OK);
    }

}
