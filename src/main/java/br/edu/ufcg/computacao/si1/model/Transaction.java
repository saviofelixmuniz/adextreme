package br.edu.ufcg.computacao.si1.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nicacio on 15/03/17.
 */
@Entity(name = "Transaction")
@Table(name = "tb_transaction")
public class Transaction {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "post_date", nullable = false)
    private Date postDate;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "note")
    private String note;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "owner")
    private String owner;

    @Column(name = "transactionType")
    private String transactionType;

    public Transaction() {

    }

    public Transaction(String title, Date postDate, double price, String note, String type, String owner, String transactionType) {
        this.title = title;
        this.postDate = postDate;
        this.price = price;
        this.note = note;
        this.type = type;
        this.owner = owner;
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostDate() {
        return DATE_FORMAT.format(postDate);
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        owner = owner;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
