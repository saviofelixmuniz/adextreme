package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.UserForm;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody UserForm userForm){

        if (usuarioService.getByEmail(userForm.getEmail()).isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        User user = usuarioService.create(userForm);

        return new ResponseEntity(user, HttpStatus.CREATED);
    }


}