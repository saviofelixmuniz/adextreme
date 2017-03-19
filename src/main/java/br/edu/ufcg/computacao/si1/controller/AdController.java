package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.compare.ad.AdComparatorEnum;
import br.edu.ufcg.computacao.si1.model.compare.ad.FactoryAdCompare;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.model.form.PurchaseForm;
import br.edu.ufcg.computacao.si1.service.AdServiceImpl;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/user")
public class AdController {

    @Autowired
    private AdServiceImpl anuncioService;

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/ads/all", method = RequestMethod.GET)
    public ResponseEntity<Collection> getAllAds(){
        Collection<Ad> anun = anuncioService.getAll();
        System.out.println(anun);
        return new ResponseEntity<Collection>(anuncioService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Collection> getByUser(@PathVariable Long userID) {
        return new ResponseEntity<Collection>(anuncioService.getByUserId(userID), HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/bought/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Collection> getBoughtAds(@PathVariable Long userID) {
        Collection<Ad> response = anuncioService.getByBuyer(userID);
        Collections.sort((List) response, (Comparator) FactoryAdCompare.getComparator(AdComparatorEnum.DATE));
        return new ResponseEntity<Collection>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/ads", method = RequestMethod.POST)
    public ResponseEntity registerAd(@RequestBody AdForm adForm){

        if (adForm.getIdOwner() != null && !usuarioService.getById(adForm.getIdOwner()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário não existe!");
        }

        Ad ad = anuncioService.create(adForm);
        return new ResponseEntity(ad, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ads/purchase", method = RequestMethod.POST)
    public ResponseEntity purchase(@RequestBody PurchaseForm purchaseForm){
        //id item, id comprador

        Ad ad = anuncioService.findById(purchaseForm.getAdId());

        if (ad == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este anúncio não existe.");

        User seller = usuarioService.findById(ad.getIdOwner()); // vendedor
        User buyer = usuarioService.findById(purchaseForm.getBuyerId()); // comprador

        if (seller == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este vendedor não existe.");
        if (buyer == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este Comprador não existe.");

        ad.setAvailable(false);
        ad.setBuyerId(buyer.getId());
        ad.handleTransaction(seller, buyer);

        anuncioService.update(ad);
        usuarioService.update(seller);
        usuarioService.update(buyer);

        return new ResponseEntity(ad, HttpStatus.OK);
    }
}