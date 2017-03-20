package br.edu.ufcg.computacao.si1.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus Oliveira on 08/12/16.
 */
@Entity(name = "Ad")
@Table(name="tb_ad")
public class Ad {

    private static final String[] types = new String[] {"movel", "imovel", "emprego"};
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)       private String title;
    @Column(name = "available", nullable = false)   private boolean available;
    @Column(name = "post_date", nullable = false)   private Date postDate;
    @Column(name = "price", nullable = false)       private double price;
    @Column(name = "note")                          private Integer note;
    @Column(name = "type", nullable = false)        private String type;
    @Column(name = "idOwner")                       private Long idOwner;
    @Column(name = "buyerId")                       private Long buyerId;
    @Column(name = "owner")                         private String owner;


    public Ad(String title, double price, String type, Long idOwner, String owner) {
        this.title = title;
        this.postDate = new Date();
        this.price = price;
        this.note = 0;
        this.type = type;
        this.idOwner = idOwner;
        this.owner = owner;
        this.buyerId = null;
        this.available = true;
    }

    public Ad() {
        this.title = "";
        this.postDate = new Date();
        this.price = 0;
        this.note = 0;
        this.type = "";
        this.buyerId = null;
    }

    // -----------------------
    // ---> gets and sets <---
    // -----------------------

    public static String[] getTypes() {
        return types;
    }

    public static DateFormat getDateFormat() {
        return DATE_FORMAT;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getPostDate() {
        return postDate;
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

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad)) return false;

        Ad ad = (Ad) o;

        if (Double.compare(ad.getPrice(), getPrice()) != 0) return false;
        if (!getId().equals(ad.getId())) return false;
        if (!getTitle().equals(ad.getTitle())) return false;
        if (!getPostDate().equals(ad.getPostDate())) return false;
        if (getNote() != null ? !getNote().equals(ad.getNote()) : ad.getNote() != null) return false;
        return getType().equals(ad.getType());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getPostDate().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getNote() != null ? getNote().hashCode() : 0);
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postDate=" + getPostDate() +
                ", price=" + price +
                ", note=" + note +
                ", type='" + type + '\'' +
                '}';
    }


    // -----------------
    // ---> methods <---
    // -----------------


    public void handleTransaction(User vendor, User buyer) {

         vendor.creditBalance(this.price);
         buyer.debitBalance(this.price);
    }

}