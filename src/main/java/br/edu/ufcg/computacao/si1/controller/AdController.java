package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.model.form.PurchaseForm;
import br.edu.ufcg.computacao.si1.service.AdServiceImpl;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class AdController {

    @Autowired
    private AdServiceImpl anuncioService;

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public ResponseEntity<Collection> anuncios(){
        Collection<Ad> anun = anuncioService.getAll();
        System.out.println(anun);
        return new ResponseEntity<Collection>(anuncioService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ads", method = RequestMethod.POST)
    public ResponseEntity cadastroAnuncio(@RequestBody AdForm adForm){

        if (adForm.getIdOwner() != null && !usuarioService.getById(adForm.getIdOwner()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário não existe!");
        }

        Ad ad = anuncioService.create(adForm);
        return new ResponseEntity(ad, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity purchase(@RequestBody PurchaseForm purchaseForm){
        //id item, id comprador

        Ad ad = anuncioService.findById(purchaseForm.getIdAnuncio());

        if (ad == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este anúncio não existe.");

        User vendor = usuarioService.findById(ad.getIdOwner()); // vendedor
        User purchaser = usuarioService.findById(purchaseForm.getIdComprador()); // comprador

        if (vendor == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este vendedor não existe.");
        if (purchaser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este Comprador não existe.");

        User[] resultado = ad.toSell(vendor, purchaser);

        anuncioService.delete(purchaseForm.getIdAnuncio());
        usuarioService.update(resultado[0]);
        usuarioService.update(resultado[1]);

        return new ResponseEntity(resultado[1], HttpStatus.OK);

    }
}