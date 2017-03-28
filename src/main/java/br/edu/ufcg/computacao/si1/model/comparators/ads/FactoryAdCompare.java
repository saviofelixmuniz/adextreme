package br.edu.ufcg.computacao.si1.model.comparators.ads;

import br.edu.ufcg.computacao.si1.model.comparators.ads.AdComparator;
import br.edu.ufcg.computacao.si1.model.comparators.ads.AdComparatorEnum;

/**
 * Created by saviomuniz on 19/03/17.
 */
public class FactoryAdCompare {
    public static AdComparator getComparator(AdComparatorEnum adComparator) {
        return adComparator.getInstance();
    }
}
