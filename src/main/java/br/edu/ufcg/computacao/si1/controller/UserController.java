package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ResponseEntity<Double>(usuarioService.getById(userID).get().getCredit(), HttpStatus.OK);
    }


}
