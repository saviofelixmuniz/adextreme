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

    @Column(unique = true)              private String email;
    @Column(name = "name")              private String name;
    @Column(name = "password")          private String password;
    @Column(name = "role") @Enumerated(EnumType.STRING)    private PersonType role;
    @Column(name = "credit")@NotNull    private Double credit;
    @Column(name = "note")              private Integer note;

    public User() {}

    public User(String name, String email, String password, PersonType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.credit = new Double(0);
        this.note = new Integer(0);
    }

    // -----------------------
    // ---> gets and sets <---
    // -----------------------

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

    public PersonType getRole() {
        return role;
    }

    public void setRole(PersonType role) {
        this.role = role;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }


    // -----------------
    // ---> methods <---
    // -----------------


    public void creditBalance(Double price) {credit += price;}

    public void debitBalance(Double price) {credit -= price;}
}