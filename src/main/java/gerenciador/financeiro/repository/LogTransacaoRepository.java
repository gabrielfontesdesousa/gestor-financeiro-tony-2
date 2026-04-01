package gerenciador.financeiro.repository;

import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.model.LogTransacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDateTime;
import java.util.List;
import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.model.LogTransacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDateTime;
import java.util.List;

public class LogTransacaoRepository {
    private final JdbcTemplate template;

    public LogTransacaoRepository(JdbcTemplate template) {
        this.template = template;
    }
    public void registrarLog (LogTransacao logTransacao) {
        try {
            String query = "INSERT INTO log_transacao VALUES (?,?,?,?)";
            template.update(query);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<LogTransacao> listarTodos() {
        try {
            String query = "SELECT * FROM log_transacao";
            List<LogTransacao> result = template.query(query, new BeanPropertyRowMapper<>(LogTransacao.class));
            new LogTransacao(LocalDateTime.now(), "LIST SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return result;
        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), "LIST FAILED", StatusTransacao.FALHA);
            return null;
        }
    }

    public List<LogTransacao> listarPorStatus() {
        try {
            String query = "SELECT * FROM log_transacao GROUP BY status ";
            List<LogTransacao> result = template.query(query, new BeanPropertyRowMapper<>(LogTransacao.class));
            new LogTransacao(LocalDateTime.now(), "LIST SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return result;
        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), "LIST FAILED", StatusTransacao.FALHA);
            return null;
        }
    }

    public List<LogTransacao> listarPorTransacao() {
        try {
            String query = "SELECT * FROM log_transacao GROUP BY transacao_id";
            List<LogTransacao> result = template.query(query, new BeanPropertyRowMapper<>(LogTransacao.class));
            new LogTransacao(LocalDateTime.now(), "LIST SUCCESS", StatusTransacao.OPERACAO_CONCLUIDA);
            return result;
        } catch (Exception e) {
            new LogTransacao(LocalDateTime.now(), "LIST FAILED", StatusTransacao.FALHA);
            return null;
        }
    }
}

