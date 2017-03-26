package br.edu.ufcg.computacao.si1.model.comparators.users;

import br.edu.ufcg.computacao.si1.model.User;

import java.util.Comparator;

/**
 * Created by saviomuniz on 25/03/17.
 */
public interface UserComparator extends Comparator<User> {
    @Override
    public int compare(User user, User anotherUser) ;
}
