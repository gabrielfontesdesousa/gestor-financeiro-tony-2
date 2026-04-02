package gerenciador.financeiro.model;

import gerenciador.financeiro.enums.StatusTransacao;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class LogTransacao {
    @NotNull(message = "Campo não pode ser nulo")
    @PastOrPresent(message = "A data deve estar no passado ou presente")
    private LocalDateTime dtHora;
    @NotBlank(message = "O campo não pode estar em branco")
    @Size(min = 3, max = 255, message = "O campo deve ter entre 3 e 255 caracteres")
    private String descricao;
    private StatusTransacao status;

    public LogTransacao(LocalDateTime dtHora, String descricao, StatusTransacao status) {
        this.dtHora = dtHora;
        this.descricao = descricao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogTransacao{" +
                "dtHora=" + dtHora +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }

    public LocalDateTime getDtHora() {
        return dtHora;
    }

    public void setDtHora(LocalDateTime dtHora) {
        this.dtHora = dtHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
}
