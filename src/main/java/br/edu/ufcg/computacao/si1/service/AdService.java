package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.form.AdForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AdService {

    /**
     * Create an announcement in system
     * @param adForm formulary in "model/form/AdForm"
     * @return Ad Object
     */
    Ad create(AdForm adForm);

    Ad getById(Long id);

    Collection<Ad> getAdByUserId(Long id);

    Collection<Ad> getAdByBuyerId(Long id);

    Collection<Ad> getTransactions(Long id, Boolean available);

    Collection<Ad> getByAvailable();

    Collection<Ad> getByType(String type);

    Collection<Ad> getAll();

    boolean update(Ad ad);

    boolean delete(Long id);

}