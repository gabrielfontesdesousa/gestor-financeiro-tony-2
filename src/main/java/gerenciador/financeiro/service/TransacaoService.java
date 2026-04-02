package gerenciador.financeiro.service;

import gerenciador.financeiro.db.ConexaoDB;
import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.model.Transacao;
import gerenciador.financeiro.repository.TransacaoRepository;
import gerenciador.financeiro.util.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class TransacaoService {

    private TransacaoRepository repository;
    private final Validator validator;

    public TransacaoService(TransacaoRepository repository) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        this.repository = repository;
    }

    public void cadastrarTransacao(Transacao transacao) {
        ValidatorUtil.validar(transacao);
        transacao.setStatus("Operação Concluida");

        repository.salvar(transacao);
        System.out.println("Transação cadastrada com sucesso!");
    }

    public List<Transacao> listarTransacoes() {
        return repository.listarTodas();
    }

    public Transacao buscarTransacaoPorId(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return null;
        }

        return repository.buscarPorId(id);
    }

    public void removerTransacao(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        boolean removido = repository.deletar(id);

        if (removido) {
            System.out.println("Transação removida com sucesso!");
        } else {
            System.out.println("Transação não encontrada.");
        }
    }
}