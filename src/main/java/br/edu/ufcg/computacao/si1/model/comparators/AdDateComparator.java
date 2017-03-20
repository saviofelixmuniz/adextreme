package br.edu.ufcg.computacao.si1.model.comparators;

import br.edu.ufcg.computacao.si1.model.Ad;

/**
 * Created by saviomuniz on 19/03/17.
 */
public class AdDateComparator implements AdComparator {
    @Override
    public int compare(Ad ad, Ad anotherAd) {
        return (int) -(ad.getPostDate().getTime() - anotherAd.getPostDate().getTime());
    }
}
