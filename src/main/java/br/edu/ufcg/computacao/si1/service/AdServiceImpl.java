package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Service
public class AdServiceImpl implements AdService {

    private AdRepository adRepository;

    public AdRepository getAdRepository() {
        return adRepository;
    }

    @Autowired
    public void setAdRepository(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Collection<Ad> getAdByUserId(Long id) {
        return adRepository.findByIdOwner(id);
    }

    @Override
    public Collection<Ad> getAdByBuyerId(Long id) {
        return adRepository.findByBuyerId(id);
    }

    @Override
    public Collection<Ad> getTransactions (Long id, Boolean available) {
        return adRepository.getTransactions(id, available);
    }

    @Override
    public Ad create(AdForm adForm) {
        Ad ad = new Ad(adForm.getTitle(), adForm.getPrice(), adForm.getType(), adForm.getIdOwner(), adForm.getOwner());
        return adRepository.save(ad);
    }

    @Override
    public Ad getById(Long id) {
        return adRepository.findOne(id);
    }

    @Override
    public Collection<Ad> getByType(String tipo) {
        return adRepository.findAll().stream()
                .filter(anuncio -> anuncio.getType().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Ad> getByAvailable() {
        return adRepository.findByAvailable(true);
    }

    @Override
    public Collection<Ad> getAll() {
        /* aqui nao seria um metodo para pegar todos? e teriamos um metodo para pegar separadamente todos?*/
        return adRepository.findAll();
    }

    @Override
    public boolean update(Ad ad) {
        if (adRepository.exists(ad.getId())) {
            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (adRepository.exists(id)) {
            adRepository.delete(id);
            return true;
        }
        return false;
    }
}