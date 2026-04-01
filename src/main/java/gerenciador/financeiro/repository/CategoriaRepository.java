package gerenciador.financeiro.repository;
import gerenciador.financeiro.model.Categoria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoriaRepository {

    private final JdbcTemplate template;

    public CategoriaRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void salvar(Categoria categoria) {
        String sql = "INSERT INTO categoria VALUES (DEFAULT, ?, ?);";
        template.update(sql, categoria.getNome(), categoria.getDescricao());
    }

    public List<Categoria> listarTodas(){
        String sql = "SELECT * FROM categoria;";
        List<Categoria> categoriaList = template.query(sql, new BeanPropertyRowMapper<>(Categoria.class));
        return categoriaList;
    }

    public Categoria buscarPorId(Integer id){
        String sql = "SELECT * FROM categoria WHERE id = ?";
        Categoria categoria = template.queryForObject(sql, new BeanPropertyRowMapper<>(Categoria.class), id);
        return categoria;
    }

    public Categoria buscarPorNome(String nome){
        String sql = "SELECT * FROM categoria WHERE UPPER(nome) = ?";
        String parametroLike = nome.toUpperCase();
        Categoria categoria = template.queryForObject(sql, new BeanPropertyRowMapper<>(Categoria.class) ,parametroLike);
        return categoria;
    }

    public void atualizar(Integer id, Categoria categoriaAtualizada){
        String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id = ?";
        template.update(sql,categoriaAtualizada.getNome(), categoriaAtualizada.getDescricao(), id);

    }



    public void deletar(Integer id){
        String sql = "DELETE FROM categoria WHERE id = ?";
        template.update(sql, id);
    }
}
