package gerenciador.financeiro.service;

import gerenciador.financeiro.db.ConexaoDB;
import gerenciador.financeiro.model.LogTransacao;
import gerenciador.financeiro.repository.LogTransacaoRepository;
import gerenciador.financeiro.util.ValidatorUtil;
import org.apache.commons.logging.Log;

import java.time.LocalDateTime;
import java.util.List;

public class LogTransacaoService {
    LogTransacaoRepository logTransacaoRepository;

    public LogTransacaoService(LogTransacaoRepository logTransacaoRepository) {
        this.logTransacaoRepository = logTransacaoRepository;
    }

    public void registrarLog(LogTransacao logTransacao){
        ValidatorUtil.validar(logTransacao);
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
}
