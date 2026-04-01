package gerenciador.financeiro.model;

import java.time.LocalDate;

public class Meta {
    private Double valorMeta;
    private Double valorAtual;
    private LocalDate dtFinal;

    public Meta() {
    }

    public Meta(Double valorFinal, Double valorAtual) {
        this.valorMeta = valorFinal;
        this.valorAtual = valorAtual;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "valorMeta=" + valorMeta +
                ", valorAtual=" + valorAtual + '}';
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public LocalDate getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(LocalDate dtFinal) {
        this.dtFinal = dtFinal;
    }
    public Double porcentagemAtingido(){
        return getValorAtual() / getValorMeta() * 100;
    }
}
