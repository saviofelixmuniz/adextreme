package br.edu.ufcg.computacao.si1.model;

import br.edu.ufcg.computacao.si1.model.EnumTypes.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;


@Entity(name = "User")
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)                                  private String email;
    @Column(name = "name")                                  private String name;
    @Column(name = "password")                              private String password;
    @Column(name = "role") @Enumerated(EnumType.STRING)     private PersonType role;
    @Column(name = "credit")@NotNull                        private Double credit;
    @Column(name = "rating_sum")                            private Integer ratingSum;
    @Column(name = "rating_count")                          private Integer ratingCount;
    @Column(name = "average_rating")                       private Integer averageRating;


    @Column(name = "qualifications_alerts")
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true)
    private List<QualificationAlert> qualificationsAlerts;


    public User() {}

    public User(String name, String email, String password, PersonType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.credit = new Double(0);
        this.ratingSum = new Integer(0);
        this.ratingCount = new Integer(0);
        this.qualificationsAlerts = new LinkedList<>();
        this.averageRating = 0;
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

    public Integer getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Integer ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<QualificationAlert> getQualificationsAlerts() {
        return qualificationsAlerts;
    }

    public void setQualificationsAlerts(List<QualificationAlert> qualificationsAlerts) {
        this.qualificationsAlerts = qualificationsAlerts;
    }
    public Integer getAvarageRating() {
        return averageRating;
    }

    public void setAvarageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }


    // -----------------
    // ---> methods <---
    // -----------------


    public void creditBalance(Double price) {credit += price;}

    public void debitBalance(Double price) {credit -= price;}

    public void sumRating(Integer ratingValue) {
        if (ratingValue > 5)
            this.ratingSum += 5;
        if (ratingValue > 0 && ratingValue <= 5)
            this.ratingSum += ratingValue;

        this.ratingCount++;
        this.averageRating = calculateAverageRating();
    }

    public Integer calculateAverageRating(){
        if (ratingCount > 0)
            return ratingSum/ratingCount;

        return ratingSum;
    }

    public boolean addQualificationAlert(QualificationAlert alert) {
        if (alert == null)
            return false;
        return qualificationsAlerts.add(alert);
    }

    public void setAlertQualificatedById(Long alertId) {
        for (int i=0; i< qualificationsAlerts.size(); i++){
            if (qualificationsAlerts.get(i).getId() == alertId) {
                qualificationsAlerts.get(i).setQualified(true);
                break;
            }
        }
    }
}