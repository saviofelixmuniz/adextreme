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
    private List<QualificationAlert> qualificationAlerts;


    public User() {}

    public User(String name, String email, String password, PersonType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.credit = new Double(0);
        this.ratingSum = new Integer(0);
        this.ratingCount = new Integer(0);
        this.qualificationAlerts = new LinkedList<>();
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

    public List<QualificationAlert> getqualificationAlerts() {
        List<QualificationAlert> alertsNotQualified = new LinkedList<>();
        for (QualificationAlert alert:
             qualificationAlerts) {
            if(!alert.isQualified())
                alertsNotQualified.add(alert);
        }
        return alertsNotQualified;
    }

    public void setqualificationAlerts(List<QualificationAlert> qualificationAlerts) {
        this.qualificationAlerts = qualificationAlerts;
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
        return qualificationAlerts.add(alert);
    }

    public void setAlertQualificatedById(Long alertId) {
        for (int i=0; i< qualificationAlerts.size(); i++){
            if (qualificationAlerts.get(i).getId() == alertId) {
                qualificationAlerts.get(i).setQualified(true);
                break;
            }
        }
    }
}