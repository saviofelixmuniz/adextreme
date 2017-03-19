package br.edu.ufcg.computacao.si1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Entity(name = "User")
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    @NotNull
    private Double credit;
    @Column
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Transaction> transactions;


    public User() {

    }

    public User(String name, String email, String password, String role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.credit = new Double(0);
        this.transactions = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addBuyerTransaction(Ad ad) {
        // transação de compra
        this.transactions.add(new Transaction(ad.getTitle(), ad.getDate(), ad.getPrice(),
                                                ad.getNote(), ad.getType(), ad.getOwner(), "Purchase"));
    }

    public void addSellerTransaction(Ad ad){
        // transação de venda
        this.transactions.add(new Transaction(ad.getTitle(), ad.getDate(),
                                            ad.getPrice(), ad.getNote(), ad.getType(), ad.getOwner(), "Venda"));
    }

    public void creditBalance(Double price) {
        credit += price;
    }

    public void debitBalance(Double price) {
        credit -= price;
    }
}