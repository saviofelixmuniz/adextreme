package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.service.AdServiceImpl;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyAdController {

    @Autowired
    private AdServiceImpl anuncioService;

    @Autowired
    private UserServiceImpl usuarioService;


    @RequestMapping(value = "/company/cadastrar/anuncio", method = RequestMethod.POST)
    public ResponseEntity cadastroAnuncio(@RequestBody AdForm adForm){
        if (adForm.getIdOwner() != null && !usuarioService.getById(adForm.getIdOwner()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário não existe!");
        }
        Ad ad = anuncioService.create(adForm);
        return new ResponseEntity(ad, HttpStatus.CREATED);
    }


}