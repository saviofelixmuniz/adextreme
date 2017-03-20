package br.edu.ufcg.computacao.si1.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * Created by nicacio on 14/03/17.
 */
public enum PersonType {
    FISICA("Física"), JURIDICA("Jurídica");

    private String represents;
    PersonType(String represents) {
        this.represents = represents;
    }

    public String getRepresents() {
        return represents;
    }


}