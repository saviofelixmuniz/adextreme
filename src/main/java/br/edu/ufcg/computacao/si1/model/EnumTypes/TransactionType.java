package br.edu.ufcg.computacao.si1.model.EnumTypes;

/**
 * Created by Nicacio on 23/03/2017.
 */
public enum TransactionType {
    COMPRA("Compra"), VENDA("Venda");

    String represents;
    TransactionType(String represents) {
        this.represents = represents;
    }

    public String getRepresents() {
        return represents;
    }
}
