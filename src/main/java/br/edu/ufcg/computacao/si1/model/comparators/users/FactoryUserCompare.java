package br.edu.ufcg.computacao.si1.model.comparators.users;

/**
 * Created by saviomuniz on 25/03/17.
 */
public class FactoryUserCompare {
    public static UserComparator getComparator(UserComparatorEnum comparator) {
        return comparator.getInstance();
    }

}
