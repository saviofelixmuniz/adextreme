package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.form.AdForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AdService {

    Ad create(AdForm anuncio);

    Optional<Ad> getById(Long id);

    Collection<Ad> get(String tipo);

    Collection<Ad> getAll();

    boolean update(Ad ad);

    boolean delete(Long id);

}