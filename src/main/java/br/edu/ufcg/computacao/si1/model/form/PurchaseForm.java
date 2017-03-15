package br.edu.ufcg.computacao.si1.model.form;

/**
 * Created by nicacio on 14/03/17.
 */
public class PurchaseForm {

    private Long idComprador;
    private Long idAnuncio;

    public Long getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Long idComprador) {
        this.idComprador = idComprador;
    }

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }
}
