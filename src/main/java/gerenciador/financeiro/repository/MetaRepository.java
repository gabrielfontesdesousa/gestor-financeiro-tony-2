package gerenciador.financeiro.repository;

import gerenciador.financeiro.model.Meta;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MetaRepository {
    private final JdbcTemplate template;
    public MetaRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void salvar(Meta meta) {
        String sql = "INSERT INTO meta VALUES (DEFAULT, ?, ?, ?)";
        template.update(sql, meta.getValorMeta(), meta.getValorAtual(), meta.getDataLimite());
    }

    public List<Meta> listarTodas() {
        String sql = "SELECT * FROM meta;";
        List<Meta> metaList = template.query(sql, new BeanPropertyRowMapper<>(Meta.class));
        return metaList;
    }

    public Meta buscarPorId(Integer id) {
        String sql = "SELECT * FROM meta WHERE id = ?";
        Meta meta = template.queryForObject(sql, new BeanPropertyRowMapper<>(Meta.class), id);
        return meta;
    }

    public void atualizar(Integer id, Meta metaAtualizada) {
        String sql = "UPDATE meta SET valor_meta = ?, data_limite = ? WHERE id = ?";
        template.update(sql, metaAtualizada.getValorMeta(), metaAtualizada.getDataLimite(), id);
    }

    public void atualizarProgresso(Integer id, Double novoValorAtual){
        String sql = "UPDATE meta SET valor_atual = ? WHERE id = ?";
        template.update(sql, novoValorAtual, id);
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM meta WHERE id = ?";
        template.update(sql, id);
    }
}
