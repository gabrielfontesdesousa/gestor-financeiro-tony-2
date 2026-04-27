package gerenciador.financeiro.service;

import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

public class CategoriaServiceTest {
    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void deveCadastrarCategoriaComSucesso() {
        Categoria categoria = new Categoria("Alimentação", "Gastos com comida");

        service.cadastrarCategoria(categoria);

        verify(repository, times(1)).salvar(categoria);
    }

    @Test
    void deveLancarErroQuandoNomeForVazio() {

        Categoria categoria = new Categoria("", "desc");

        RuntimeException erro = assertThrows(RuntimeException.class, () -> {
            service.cadastrarCategoria(categoria);
        });

        assertTrue(erro.getMessage().contains("não pode estar em branco"));
        assertTrue(erro.getMessage().contains("deve ter entre 3 e 255 caracteres"));
    }


    @Test
    void deveRetornarUmaListaDeCategorias() {

        Categoria categoria = new Categoria("Alimentos", "Gastos com comida");
        Categoria categoria1 = new Categoria("Bebidas", "gastos com bebidas");

        List<Categoria> listaMock = List.of(categoria, categoria1);

        when(repository.listarTodas()).thenReturn(listaMock);

        List<Categoria> resultado = service.listarCategorias();

        assertEquals(2, resultado.size());
        assertEquals("Alimentos", resultado.get(0).getNome());
        verify(repository, times(1)).listarTodas();
    }

    @Test
    void deveRetornarListaVazia() {
        List<Categoria> listaMock = new ArrayList<>();

        when(repository.listarTodas()).thenReturn(listaMock);
        List<Categoria> result = repository.listarTodas();
        assertEquals( listaMock, result);
        verify(repository, times(1)).listarTodas();
    }

    @Test
    void deveRetornarCategoriaPorIdSeExistir() {
        Integer id = 1;
        Categoria categoria = new Categoria("Alimentos", "Gastos com comida");

        when(repository.buscarPorId(id)).thenReturn(categoria);
        Categoria result = service.buscarCategoriaPorId(id);

        assertEquals(categoria.getNome(), result.getNome());
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void deveRetornarErroQuandoCategoriaIdNaoExistir() {
        Integer id = 1;

        when(repository.buscarPorId(id)).thenThrow(new EmptyResultDataAccessException(1));

        RuntimeException erro = assertThrows(RuntimeException.class, () -> {
            service.buscarCategoriaPorId(id);
        });

        assertTrue(erro.getMessage().contains("não existe"));
    }

    @Test
    void deveRetornarUmaCategoriaPorNome() {
        String nome = "Alimentos";
        Categoria categoria = new Categoria("Alimentos", "Gastos com comida");

        when(repository.buscarPorNome(nome)).thenReturn(categoria);
        Categoria result = service.buscarCategoriaPorNome(nome);

        assertEquals(categoria.getNome(), result.getNome());
        verify(repository, times(1)).buscarPorNome(nome);

    }

    @Test
    void deveLancarErroQuandoCategoriaNomeNaoExistir() {
        String nome = "Alimentos";

        when(repository.buscarPorNome(nome)).thenThrow(new EmptyResultDataAccessException(1));

        RuntimeException erro = assertThrows(RuntimeException.class, () -> {
            service.buscarCategoriaPorNome(nome);
        });

        assertTrue(erro.getMessage().contains("não existe"));
    }

    @Test
    void deveAtualizarUmaCategoria() {
        Categoria novaCategoria = new Categoria("aaa", "bbb");

        service.atualizarCategoria(1, novaCategoria);

        verify(repository, times(1)).atualizar(1, novaCategoria);
    }

    @Test
    void deveRemoverUmaCategoriaPeloId() {
        Integer id = 1;

        service.removerCategoria(1);

        verify(repository, times(1)).deletar(1);
    }

}
