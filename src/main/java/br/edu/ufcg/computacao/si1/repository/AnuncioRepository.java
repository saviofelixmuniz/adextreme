package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByIdOwner (Long id);
    List<Ad> findByBuyerId (Long id);
    List<Ad> findByAvailable (boolean available);

    @Query("SELECT a FROM Ad a WHERE a.available='false' and (a.buyerId=:id or a.idOwner=:id)")
    List<Ad> getTransactions (Long id);
}