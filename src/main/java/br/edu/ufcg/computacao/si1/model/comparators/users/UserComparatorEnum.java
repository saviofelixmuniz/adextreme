package br.edu.ufcg.computacao.si1.model.comparators.users;

/**
 * Created by saviomuniz on 25/03/17.
 */
public enum UserComparatorEnum {
    AVERAGE_RATING(new UserAverageRatingComparator()), COUNT_RATING(new UserCountRatingComparator());

    private UserComparator userComparator;

    UserComparatorEnum(UserComparator userComparator) {
        this.userComparator = userComparator;
    }

    public UserComparator getInstance () { return userComparator; }

}
