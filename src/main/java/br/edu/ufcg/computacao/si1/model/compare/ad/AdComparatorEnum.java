package br.edu.ufcg.computacao.si1.model.compare.ad;

/**
 * Created by saviomuniz on 19/03/17.
 */
public enum AdComparatorEnum {
    DATE (new AdDateComparator()), PRICE(new AdPriceComparator());

    private AdComparator comparatorInstance;

    AdComparatorEnum(AdComparator comparatorInstance) {
        this.comparatorInstance = comparatorInstance;
    }

    public AdComparator getInstance() {
        return comparatorInstance;
    }


}
