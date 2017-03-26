package br.edu.ufcg.computacao.si1.model.comparators.users;

import br.edu.ufcg.computacao.si1.model.User;

/**
 * Created by saviomuniz on 25/03/17.
 */
public class UserAverageRatingComparator implements UserComparator {
    @Override
    public int compare(User user, User anotherUser) {
        return -(user.calculateAverageRating() - anotherUser.calculateAverageRating());
    }
}
