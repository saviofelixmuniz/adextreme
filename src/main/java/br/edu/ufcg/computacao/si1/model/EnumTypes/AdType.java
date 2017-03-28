package br.edu.ufcg.computacao.si1.model.EnumTypes;

/**
 * Created by Nicacio on 25/03/2017.
 */
public enum AdType {
    EMPTY(""), MOVEL("Móvel"), IMOVEL("Imóvel"), EMPREGO("Emprego");

    private String represents;
    AdType(String represents) {
        this.represents = represents;
    }

    public String getRepresents() {return represents;}
}
