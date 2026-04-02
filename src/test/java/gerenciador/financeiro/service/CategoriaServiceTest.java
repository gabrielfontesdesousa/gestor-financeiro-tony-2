package gerenciador.financeiro.service;

import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

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

       assertEquals("Erros de validação:\nCampo 'nome': O campo deve ter entre 3 e 255 caracteres\nCampo 'nome': O campo não pode estar em branco\n", erro.getMessage());
    }


    @Test
    void deveRetornarUmaListaDeCategorias(){
        CategoriaRepository repository = mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);

        Categoria categoria = new Categoria("Alimentos", "Gastos com comida");
        Categoria categoria1 = new Categoria("Bebidas", "gastos com bebidas");

        List<Categoria> listaMock = List.of(categoria, categoria1);

        when(repository.listarTodas()).thenReturn(listaMock);

        List<Categoria> resultado = service.listarCategorias();

        assertEquals(2, resultado.size());
        assertEquals("Alimentos", resultado.get(0).getNome());
    }

}
