package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);

}
