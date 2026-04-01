package gerenciador.financeiro.service;


import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.repository.CategoriaRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {

        this.repository = repository;
    }

    public void cadastrarCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new RuntimeException("Nome da categoria é um campo obrigatorio!");
        }
        if (categoria.getDescricao() == null || categoria.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição da categoria é um campo obrigatorio!");
        }
        if (categoria.getNome().length() < 3) {
            throw new RuntimeException("Nome deve conter ao menos três caracteres");
        }
        repository.salvar(categoria);
    }

    public List<Categoria> listarCategorias() {
        if (repository.listarTodas().isEmpty()){
            throw new RuntimeException("Não existem categorias cadastradas");
        }
        return repository.listarTodas();
    }

    public Categoria buscarCategoriaPorId(Integer id) {
        try {
            return repository.buscarPorId(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Categoria com ID " + id + " não existe");
        }
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new RuntimeException("Nome da categoria é um campo obrigatorio!");
        }
        if (nome.length() < 3) {
            throw new RuntimeException("Nome deve conter ao menos três caracteres");
        }
        try {
            return repository.buscarPorNome(nome);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarCategoria(Integer id, Categoria categoria) {
        repository.atualizar(id, categoria);
    }

    public void removerCategoria(Integer id) {
        repository.deletar(id);
    }
}
