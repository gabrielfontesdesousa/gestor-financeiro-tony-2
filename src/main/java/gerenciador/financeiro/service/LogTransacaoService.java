package gerenciador.financeiro.service;

import gerenciador.financeiro.db.ConexaoDB;
import gerenciador.financeiro.model.LogTransacao;
import gerenciador.financeiro.repository.LogTransacaoRepository;
import org.apache.commons.logging.Log;

import java.time.LocalDateTime;
import java.util.List;

public class LogTransacaoService {
    LogTransacaoRepository logTransacaoRepository;

    public LogTransacaoService(LogTransacaoRepository logTransacaoRepository) {
        this.logTransacaoRepository = logTransacaoRepository;
    }

    public void registrarLog(LogTransacao logTransacao){
        validar(logTransacao);

        if (logTransacao.getDtHora() == null) {
            logTransacao.setDtHora(LocalDateTime.now());
        }
        logTransacaoRepository.registrarLog(logTransacao);
    }
    public List<LogTransacao> listarLogs(LogTransacao logTransacao){
        List<LogTransacao> result = logTransacaoRepository.listarTodos();
        return result;
    }
    public List<LogTransacao> listarLogsPorStatus(){
        List<LogTransacao> result = logTransacaoRepository.listarPorStatus();
        return result;
    }
    public List<LogTransacao> listarLogsPorTransacao(){
        List<LogTransacao> result = logTransacaoRepository.listarPorTransacao();
        return result;
    }
    private void validar(LogTransacao log) {
        if (log == null) {
            throw new IllegalArgumentException("Log não pode ser nulo.");
        }

        if (log.getDescricao() == null || log.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição do log obrigatória.");
        }

        if (log.getStatus() == null) {
            throw new IllegalArgumentException("Status do log obrigatório.");
        }
    }
}
