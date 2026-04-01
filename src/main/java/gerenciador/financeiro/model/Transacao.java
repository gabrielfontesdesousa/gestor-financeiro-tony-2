package gerenciador.financeiro.model;

import gerenciador.financeiro.enums.StatusTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private Integer id;
    private Double valor;
    private LocalDateTime dataHora;
    private String status;
    private String descricao;
    private String tipo;
    private Integer categoriaId;

    public Transacao() {
    }

    public Transacao(Double valor, LocalDateTime dataHora, String descricao, String tipo, Integer categoriaId) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
    }

    public Transacao(Double valor, LocalDateTime dataHora, String descricao, String tipo) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Transacao(Double valor, String descricao, String tipo) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    @Override
    public String toString() {
        return "Transacao:" +
                "ID: " + id + '\n' +
                "Valor: " + valor + '\n' +
                "Data Hora: " + dataHora + '\n' +
                "Status: " + status + '\n' +
                "Descrição: " + descricao + '\n' +
                "Tipo: " + tipo + '\n' +
                "Categoria ID: " + categoriaId;
    }
}
