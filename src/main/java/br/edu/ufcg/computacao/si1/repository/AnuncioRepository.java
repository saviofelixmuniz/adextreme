package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByIdOwner (Long id);
    List<Ad> findByBuyerId (Long id);
    List<Ad> findByAvailable (boolean available);

    //@Query("SELECT a FROM Ad a WHERE a.available='false' and (a.buyerId=:id or a.idOwner=:id)")
    @Query(value = "select a from Ad a where a.available=:available and (a.buyerId=:id or a.idOwner=:id)")
    Collection<Ad> getTransactions (@Param("id") Long id, @Param("available") Boolean available);

}