package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.EnumTypes.PersonType;
import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.UserForm;
import br.edu.ufcg.computacao.si1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    // ---> methods

    @Override
    public User create(UserForm userForm) {

        User user = null;

        if (userForm.getRole().equalsIgnoreCase(PersonType.FISICA.toString())) {
            user = new User(userForm.getName(), userForm.getEmail(),
                    userForm.getPassword(), PersonType.FISICA);
        }
        else if (userForm.getRole().equalsIgnoreCase(PersonType.JURIDICA.toString())) {
            user = new User(userForm.getName(), userForm.getEmail(),
                    userForm.getPassword(), PersonType.JURIDICA);
        } else {
            // caso de algum problema, cria usuario fisico mesmo
            user = new User(userForm.getName(), userForm.getEmail(),
                    userForm.getPassword(), PersonType.FISICA);

        }

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean update(User user) {
        if (userRepository.exists(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public User qualifyUserById(Long idUserToQualify,Long alertId, Integer ratingValue) {
        //int resp = userRepository.updateRating(idUserToQualify, (ratingValue+ratingSum));
        User u = userRepository.findOne(idUserToQualify);
        if (u != null) {
            u.sumRating(ratingValue);
            u.setAlertQualificatedById(alertId);
            this.update(u);
        }
        return u;
    }

}