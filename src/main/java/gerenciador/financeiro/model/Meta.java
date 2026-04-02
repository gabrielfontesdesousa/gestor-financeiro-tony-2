package gerenciador.financeiro.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class Meta {

    private Integer id;
    @NotNull(message = "Campo não pode estar em branco")
    @Positive(message = "Meta não pode ser um valor negativo")
    private Double valorMeta;
    @PositiveOrZero(message = "Valor atual deve ser um valor positivo ou igual a 0")
    private Double valorAtual;
    @Future(message = "Data deve ser futura")
    private LocalDate dataLimite;

    public Meta() {
    }

    public Meta(Double valorFinal, Double valorAtual) {
        this.valorMeta = valorFinal;
        this.valorAtual = valorAtual;
    }

    public Meta(Double valorFinal, Double valorAtual, LocalDate dataLimite) {
        this.valorMeta = valorFinal;
        this.valorAtual = valorAtual;
        this.dataLimite = dataLimite;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    @Override
    public String toString() {
        return "Meta: " + "\n" +
                "ID: " + id + "\n" +
                "Valor da Meta: " + valorMeta + "\n" +
                "Valor Atual: " + valorAtual  + "\n" +
                "Data Limite: " + dataLimite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }
}
