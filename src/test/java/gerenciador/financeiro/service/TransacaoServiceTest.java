package gerenciador.financeiro.service;

import gerenciador.financeiro.model.Transacao;
import gerenciador.financeiro.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransacaoServiceTest {
    @Mock
    TransacaoRepository repository;

    @InjectMocks
    TransacaoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarTransacaoComSucesso() {
        Transacao transacao = new Transacao(100.0, LocalDateTime.now(), "Gastei", "DESPESA", 1);

        service.cadastrarTransacao(transacao);

        verify(repository, times(1)).salvar(transacao);
    }

    @Test
    void deveLancarErroQuandoParametroInvalido() {
        Transacao transacao = new Transacao(-1.0, LocalDateTime.now(), "Gastei", "DESPESA", 1);

        Exception erro = assertThrows(Exception.class, () -> {
            service.cadastrarTransacao(transacao);
        });

        assertTrue(erro.getMessage().contains("Campo deve ser positivo"));
    }

    @Test
    void deveRetornarListaDeTransacoes(){
        Transacao transacao = new Transacao(100.0, LocalDateTime.now(), "Gastei", "DESPESA", 1);
        Transacao transacao1 = new Transacao(600.0, LocalDateTime.now(), "Ganhei", "RECEITA", 1);


        List<Transacao> transacaoList = List.of(transacao, transacao1);

        when(repository.listarTodas()).thenReturn(transacaoList);

        List<Transacao> result = service.listarTransacoes();

        assertEquals(2, result.size());
        assertEquals(100.0, result.get(0).getValor());
    }

    @Test
    void deveRetornarTransacaoPorId(){
        Integer id = 1;
        Transacao transacao1 = new Transacao(600.0, LocalDateTime.now(), "Ganhei", "RECEITA", 1);

        when(repository.buscarPorId(id)).thenReturn(transacao1);

        Transacao result = service.buscarTransacaoPorId(id);

        assertEquals(transacao1.getValor(), result.getValor());
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void deveRemoverTransacaoPorId(){
        Integer id = 1;

        service.removerTransacao(id);

        verify(repository, times(1)).deletar(id);
    }
}
