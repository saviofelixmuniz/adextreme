package br.edu.ufcg.computacao.si1.model.comparators.ads;

import br.edu.ufcg.computacao.si1.model.Ad;

import java.util.Comparator;

/**
 * Created by saviomuniz on 19/03/17.
 */
public interface AdComparator extends Comparator<Ad> {
    @Override
    public int compare(Ad ad, Ad anotherAd) ;
}
