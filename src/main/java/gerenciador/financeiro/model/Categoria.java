package gerenciador.financeiro.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Categoria {
    @NotBlank(message = "O campo não pode estar em branco")
    @Size(min = 3, max = 255, message = "O campo deve ter entre 3 e 255 caracteres")
    private String nome;

    @NotBlank(message = "O campo não pode estar em branco")
    @Size(min = 3, max = 255, message = "O campo deve ter entre 3 e 255 caracteres")
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
