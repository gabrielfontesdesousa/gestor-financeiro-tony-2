package gerenciador.financeiro.model;

import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.enums.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private Integer id;
    private Double valor;
    private LocalDateTime dtHora;
    private StatusTransacao status;
    private String descricao;
    private String tipoTransacao;
    // ADICIONADO: campo categoriaId
    private Integer categoriaId;

    public Transacao() {
    }

    // Construtor completo com data e categoria
    public Transacao(Double valor, LocalDateTime dtHora, String descricao, String tipoTransacao, Integer categoriaId) {
        this.valor = valor;
        this.dtHora = dtHora;
        this.descricao = descricao;
        this.tipoTransacao = tipoTransacao;
        this.categoriaId = categoriaId;
    }

    // Construtor sem categoria (mantido para compatibilidade)
    public Transacao(Double valor, LocalDateTime dtHora, String descricao, String tipoTransacao) {
        this.valor = valor;
        this.dtHora = dtHora;
        this.descricao = descricao;
        this.tipoTransacao = tipoTransacao;
    }

    // Construtor mínimo (mantido para compatibilidade)
    public Transacao(Double valor, String descricao, String tipoTransacao) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipoTransacao = tipoTransacao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDateTime getDtHora() { return dtHora; }
    public void setDtHora(LocalDateTime dtHora) { this.dtHora = dtHora; }

    public StatusTransacao getStatus() { return status; }
    public void setStatus(StatusTransacao status) { this.status = status; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipoTransacao() { return tipoTransacao; }
    public void setTipoTransacao(String tipoTransacao) { this.tipoTransacao = tipoTransacao; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", valor=" + valor +
                ", dtHora=" + dtHora +
                ", status=" + status +
                ", descricao='" + descricao + '\'' +
                ", tipoTransacao=" + tipoTransacao +
                ", categoriaId=" + categoriaId + // CORRIGIDO: era 'id' por engano
                '}';
    }
}
