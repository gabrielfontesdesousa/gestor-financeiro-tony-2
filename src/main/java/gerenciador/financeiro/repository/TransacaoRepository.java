package gerenciador.financeiro.repository;

import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.enums.TipoTransacao;
import gerenciador.financeiro.model.LogTransacao;
import gerenciador.financeiro.model.Transacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class TransacaoRepository {

    private JdbcTemplate jdbcTemplate;

    public TransacaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Transacao transacao) {
        try {
            // CORRIGIDO: inclui data_hora e categoria_id no INSERT
            String sql = """
                    INSERT INTO transacao (valor, data_hora, descricao, tipo, status, categoria_id)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;

            jdbcTemplate.update(
                    sql,
                    transacao.getValor(),
                    transacao.getDataHora(),
                    transacao.getDescricao(),
                    transacao.getTipo(),
                    transacao.getStatus(),
                    transacao.getCategoriaId()
            );

            new LogTransacao(LocalDateTime.now(), "INSERT SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
        }
    }

    public List<Transacao> listarTodas() {
        try {
            String sql = "SELECT * FROM transacao";
            List<Transacao> lista = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Transacao.class)
            );

            new LogTransacao(LocalDateTime.now(), "LIST SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return lista;

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
            return null;
        }
    }

    public Transacao buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM transacao WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Transacao.class),
                    id
            );
        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
            return null;
        }
    }

    public List<Transacao> listarPorTipo(TipoTransacao tipo) {
        try {
            String sql = "SELECT * FROM transacao WHERE tipo = ?";
            List<Transacao> lista = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Transacao.class),
                    tipo.name()
            );

            new LogTransacao(LocalDateTime.now(), "LIST BY TIPO SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return lista;

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
            return null;
        }
    }

    public List<Transacao> listarPorCategoria(Integer categoriaId) {
        try {
            String sql = "SELECT * FROM transacao WHERE categoria_id = ?";
            List<Transacao> lista = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Transacao.class),
                    categoriaId
            );

            new LogTransacao(LocalDateTime.now(), "LIST BY CATEGORIA SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return lista;

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
            return null;
        }
    }

    public void atualizar(Transacao transacao) {
        try {
            String sql = """
                    UPDATE transacao
                    SET valor = ?, data_hora = ?, status = ?, descricao = ?, tipo = ?, categoria_id = ?
                    WHERE id = ?
                    """;

            jdbcTemplate.update(
                    sql,
                    transacao.getValor(),
                    transacao.getDataHora(),
                    transacao.getStatus(),
                    transacao.getDescricao(),
                    transacao.getTipo(),
                    transacao.getCategoriaId(),
                    transacao.getId()
            );

            new LogTransacao(LocalDateTime.now(), "EDIT SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
        }
    }

    public boolean deletar(Integer id) {
        try {
            String sql = "DELETE FROM transacao WHERE id = ?";
            jdbcTemplate.update(sql, id);

            new LogTransacao(LocalDateTime.now(), "DELETE SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return true;

        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), e.getMessage(), StatusTransacao.FALHA);
            return false;
        }
    }
}
