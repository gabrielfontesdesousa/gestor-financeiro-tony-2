package gerenciador.financeiro.service;

import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoriaServiceTest {
    @Test
    void deveCadastrarCategoriaComSucesso(){
        CategoriaRepository repository = mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);

        Categoria categoria = new Categoria("Alimentação", "Gastos com comida");

        service.cadastrarCategoria(categoria);

        verify(repository, times(1)).salvar(categoria);
    }

    @Test
    void deveLancarErroQuandoNomeForVazio(){
        CategoriaRepository repository = mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);

        Categoria categoria = new Categoria("", "desc");

        RuntimeException erro = assertThrows(RuntimeException.class, () -> {
            service.cadastrarCategoria(categoria);
        });

       assertEquals("Nome da categoria é um campo obrigatorio!", erro.getMessage());
    }

}
