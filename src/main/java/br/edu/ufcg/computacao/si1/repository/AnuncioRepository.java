package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByIdOwner (Long id);
    List<Ad> findByAvailable (boolean available);
    List<Ad> findByBuyerId (Long id);
}   