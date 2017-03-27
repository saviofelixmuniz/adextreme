package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.UserForm;
import java.util.Collection;

/**
 * Created by Marcus Oliveira on 28/12/16.
 * Modificated by Nicácio Oliveira on 19/03/17.
 */
public interface UserService {

    /**
     * Create an user in system
     * @param userForm formulary in "model/form/UserForm"
     * @return User Object
     */
    User create(UserForm userForm);

    User getById(Long id);

    User getByEmail(String email);

    Collection<User> getAll();

    boolean update(User user);

    boolean delete(Long id);

    User qualifyUserById(Long idUserToQualify, Long alertId, Integer ratingValue, Long qualifierID);
}
