package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    /**
     *
     * @param id Owner
     * @return
     */
    List<Ad> findByIdOwner(Long id);

    /**
     *
     * @param id Buyer
     * @return
     */
    List<Ad> findByBuyerId (Long id);

    /**
     *
     * @param available boolean
     * @return
     */
    List<Ad> findByAvailable (boolean available);

    /**
     * Gets transitions according to boolean available (true or false)
     * @param id
     * @param available
     * @return
     */
    @Query(value = "select a from Ad a where a.available=:available and (a.buyerId=:id or a.idOwner=:id)")
    Collection<Ad> getTransactions (@Param("id") Long id, @Param("available") Boolean available);


    /**
     * Updates the note value of the Ad
     * @param idAd
     * @param none
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Ad a set a.note=:note where a.id=:idAd")
    int updateNote(@Param("idAd") Long idAd, @Param("note") Integer none);

}