package br.edu.ufcg.computacao.si1.model.form;

/**
 * Created by nicacio on 14/03/17.
 */
public class PurchaseForm {

    private Long buyerId;
    private Long adId;

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
