package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Ad;
import br.edu.ufcg.computacao.si1.model.form.AdForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Service
public class AdServiceImpl implements AdService {
    //TODO add validity checks

    private AnuncioRepository anuncioRepository;

    @Autowired
    public AdServiceImpl(AnuncioRepository anuncioRepository) {
        /*neste codigo apenas atribuimos o repositorio jpa ao atributo */
        this.anuncioRepository = anuncioRepository;
    }

    public AnuncioRepository getAnuncioRepository(){
        return this.anuncioRepository;
    }

    @Override
    public Ad create(AdForm adForm) {

        Ad ad = new Ad(adForm.getTitle(), adForm.getPrice(), adForm.getType(), adForm.getIdOwner(), adForm.getOwner());
        return anuncioRepository.save(ad);
    }

    @Override
    public Optional<Ad> getById(Long id) {
        /*aqui recuperamos o anuncio pelo seu id*/
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }

    public Collection<Ad> getByUserId (Long id) {
        return anuncioRepository.findByIdOwner(id);
    }

    public Collection<Ad> getByBuyer (Long id) {
        return anuncioRepository.findByBuyerId(id);
    }


    @Override
    public Collection<Ad> get(String tipo) {

        /*pegamos aqui todos os anuncios, mas retornamos os anuncios por type
        * filtrando o type, pelo equals, retornando um arrayLista*/
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getType().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Ad> getAll() {
        /*aqui retornamos todos os anuncios, sem distincao*/

        return anuncioRepository.findByAvailable(true);
    }

    @Override
    public boolean update(Ad ad) {
        if (anuncioRepository.exists(ad.getId())) {
            anuncioRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (anuncioRepository.exists(id)) {
            anuncioRepository.delete(id);
            return true;
        }
        return false;
    }

    public Ad findById(Long id) {
        return anuncioRepository.findOne(id);
    }
}