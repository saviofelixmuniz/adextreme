package br.edu.ufcg.computacao.si1.model;

import br.edu.ufcg.computacao.si1.model.EnumTypes.TransactionType;

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

    @Column(name = "user_to_qualify_id")                            private Long userToQualifyId;
    @Column(name = "ad_id")                                         private Long adId;
    @Column(name = "transaction_type")@Enumerated(EnumType.STRING)  private TransactionType transactionType; // sale or purchase
    @Column(name = "qualified")                                     private boolean qualified;

    public QualificationAlert() {}

    public QualificationAlert(Long userToQualifyId, Long adId, TransactionType transactionType, boolean qualified) {
        this.userToQualifyId = userToQualifyId;
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


    public Long getUserToQualifyId() {
        return userToQualifyId;
    }

    public void setUserToQualifyId(Long userToQualifyId) {
        this.userToQualifyId = userToQualifyId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }
}
