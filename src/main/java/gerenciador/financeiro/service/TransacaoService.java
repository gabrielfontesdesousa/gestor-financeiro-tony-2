package gerenciador.financeiro.service;

import gerenciador.financeiro.db.ConexaoDB;
import gerenciador.financeiro.enums.StatusTransacao;
import gerenciador.financeiro.model.Transacao;
import gerenciador.financeiro.repository.TransacaoRepository;

import java.util.List;

public class TransacaoService {

    private TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarTransacao(Transacao transacao) {
        if (transacao == null) {
            System.out.println("Transação inválida.");
            return;
        }

        if (transacao.getValor() == null || transacao.getValor() <= 0) {
            System.out.println("Valor da transação inválido.");
            return;
        }

        if (transacao.getDescricao() == null || transacao.getDescricao().isBlank()) {
            System.out.println("Descrição obrigatória.");
            return;
        }

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