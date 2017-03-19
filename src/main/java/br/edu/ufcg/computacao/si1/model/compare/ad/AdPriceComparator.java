package br.edu.ufcg.computacao.si1.model.compare.ad;

import br.edu.ufcg.computacao.si1.model.Ad;

/**
 * Created by saviomuniz on 19/03/17.
 */
public class AdPriceComparator implements AdComparator{
    @Override
    public int compare(Ad ad, Ad anotherAd) {
        if (ad.getPrice() > anotherAd.getPrice())
            return 1;
        else if (ad.getPrice() < anotherAd.getPrice())
            return -1;
        else
            return 0;
    }
}
