package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.comparators.AdComparatorEnum;
import br.edu.ufcg.computacao.si1.model.comparators.FactoryAdCompare;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.model.form.PurchaseForm;
import br.edu.ufcg.computacao.si1.service.AdServiceImpl;
import br.edu.ufcg.computacao.si1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AdController {

    @Autowired
    private AdServiceImpl adService;

    @Autowired
    private UserServiceImpl usuarioService;

    @RequestMapping(value = "/ads/all", method = RequestMethod.GET)
    public ResponseEntity<Collection> getAllAds(){
        Collection<Ad> anun = adService.getAll();
        return new ResponseEntity<>(anun, HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Collection> getByUser(@PathVariable Long userID) {
        Collection response = adService.getAdByUserId(userID);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/bought/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Collection> getBoughtAds(@PathVariable Long userID) {
        Collection<Ad> response = adService.getAdByBuyerId(userID);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Collections.sort((List) response, (Comparator) FactoryAdCompare.getComparator(AdComparatorEnum.DATE));
        return new ResponseEntity<Collection>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/ads", method = RequestMethod.POST)
    public ResponseEntity registerAd(@RequestBody AdForm adForm){

        if (adForm.getIdOwner() != null && usuarioService.getById(adForm.getIdOwner()) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário não existe!");
        }

        Ad ad = adService.create(adForm);
        return new ResponseEntity(ad, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ads/purchase", method = RequestMethod.POST)
    public ResponseEntity purchase(@RequestBody PurchaseForm purchaseForm){
        //id item, id comprador

        Ad ad = adService.getById(purchaseForm.getAdId());

        if (ad == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este anúncio não existe.");

        User seller = usuarioService.getById(ad.getIdOwner()); // vendedor
        User buyer = usuarioService.getById(purchaseForm.getBuyerId()); // comprador

        if (seller == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este vendedor não existe.");
        if (buyer == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este Comprador não existe.");

        ad.setAvailable(false);
        ad.setBuyerId(buyer.getId());
        ad.handleTransaction(seller, buyer);

        adService.update(ad);
        usuarioService.update(seller);
        usuarioService.update(buyer);

        return new ResponseEntity(ad, HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/transactions/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Collection> getTransactions(@PathVariable Long userID) {
        /*
        Deve passar o boolean idicando se quer as transacoes que estao true ou false
         */
        return new ResponseEntity<Collection>(adService.getTransactions(userID, false),HttpStatus.OK);
    }

    @RequestMapping(value = "/ads/{adId}/{note}")
    public ResponseEntity setNoteById(@PathVariable Long adId, @PathVariable Integer note) {
        Boolean ok = adService.setNoteById(adId, note);
        if (ok)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}