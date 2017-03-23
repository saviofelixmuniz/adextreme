package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);

    //Update user ratingSum variable
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update User u set u.ratingSum=:ratingValue where u.id=:idUser")
    int updateRating(@Param("idUser") Long idUser, @Param("ratingValue") Integer ratingValue);

}
