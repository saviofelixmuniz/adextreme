package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.UserForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    User create(UserForm userForm);

    Optional<User> getById(Long id);

    Optional<User> getByEmail(String email);

    Collection<User> getAll();

    boolean update(User user);

    boolean delete(Long id);
}
