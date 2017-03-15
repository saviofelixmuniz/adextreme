package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.User;
import br.edu.ufcg.computacao.si1.model.form.UserForm;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public User create(UserForm userForm) {

        User user = null;

        if (userForm.getRole().equals("fisica")) {
            user = new User(userForm.getName(), userForm.getEmail(),
                    userForm.getPassword(), "USER");
        }
        else if (userForm.getRole().equals("juridica")) {
            user = new User(userForm.getName(), userForm.getEmail(),
                    userForm.getPassword(), "COMPANY");
            user.setRole("COMPANY");
        }

        System.out.println(user + "estah sendo criado");
        return usuarioRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(usuarioRepository.findOne(id));
    }


    public User findById(Long id) {
        return usuarioRepository.findOne(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        System.out.println(email + "estah sendo retornado");
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    @Override
    public Collection<User> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean update(User user) {
        System.out.println(user + "estah sendo atualizado");
        if (usuarioRepository.exists(user.getId())) {
            usuarioRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (usuarioRepository.exists(id)) {
            usuarioRepository.delete(id);
            return true;
        }
        return false;
    }

    public User findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}