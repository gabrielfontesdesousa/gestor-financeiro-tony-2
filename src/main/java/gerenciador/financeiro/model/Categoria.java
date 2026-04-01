package gerenciador.financeiro.model;

public class Categoria {
    private String nome;
    private String descricao;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categoria:"
                + '\n' +
                "ID: " + id + '\n' +
                "Nome: " + nome + '\n' +
                "Descrição: " + descricao;
    }
    public Categoria() {
    }
    public Categoria(String nome, String desc) {
        this.nome = nome;
        this.descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
