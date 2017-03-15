package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.LoginForm;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by nicacio on 13/03/17.
 */
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginForm loginForm) {

        if (!usuarioService.getByEmail(loginForm.getEmail()).isPresent())
            return new ResponseEntity<LoginResponse>(HttpStatus.NOT_FOUND);

        if (loginForm.getEmail() == null || loginForm.getPassword() == null)
            return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);

        User user = usuarioService.findByEmail(loginForm.getEmail());

        if (!user.getPassword().equals(loginForm.getPassword()))
            return new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED);

        // generating token
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS512, "adExtremeKey")
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .compact();

        return new ResponseEntity<LoginResponse>(new LoginResponse(token, user), HttpStatus.OK);

    }

    private class LoginResponse {
        String token;
        User user;

        public LoginResponse(String token, User user){
            this.token = token;
            this.user = user;
        }
        public String getToken() {return token;}
        public User getUser() {return user;}


    }
}
