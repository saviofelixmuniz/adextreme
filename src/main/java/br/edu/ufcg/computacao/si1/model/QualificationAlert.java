package br.edu.ufcg.computacao.si1.model;

import javax.persistence.*;

/**
 * Created by Nicacio on 20/03/2017.
 */
@Entity(name = "Qualification_Alert")
@Table(name = "tb_qualification_alert")
public class QualificationAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "buyer_id")          private Long buyerId;
    @Column(name = "seller_id")         private Long sellerId;
    @Column(name = "ad_id")             private Long adId;
    @Column(name = "transaction_type")  private String transactionType;
    @Column(name = "qualified")         private boolean qualified;

    public QualificationAlert(){

    }

    public QualificationAlert(Long buyerId, Long sellerId, Long adId, String transactionType, boolean qualified) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.adId = adId;
        this.transactionType = transactionType;
        this.qualified = qualified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }
}
