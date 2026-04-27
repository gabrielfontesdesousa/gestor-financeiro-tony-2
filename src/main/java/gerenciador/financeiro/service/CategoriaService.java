package gerenciador.financeiro.service;


import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.repository.CategoriaRepository;
import gerenciador.financeiro.util.ValidatorUtil;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {

        this.repository = repository;
    }

    public void cadastrarCategoria(Categoria categoria) {
            ValidatorUtil.validar(categoria);
            repository.salvar(categoria);
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categoriaList = repository.listarTodas();
        if (categoriaList.isEmpty()){
            System.out.println("Não existem categorias cadastradas");
        }
        return categoriaList;
    }

    public Categoria buscarCategoriaPorId(Integer id) {
        try {
            return repository.buscarPorId(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Categoria com ID " + id + " não existe");
        }
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        try {
            return repository.buscarPorNome(nome);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Categoria com o nome " + nome + " não existe");
        }
    }

    public void atualizarCategoria(Integer id, Categoria categoria) {
        ValidatorUtil.validar(categoria);
        repository.atualizar(id, categoria);
    }

    public void removerCategoria(Integer id) {
        Categoria categoria = buscarCategoriaPorId(id);

        if (categoria == null){
            System.out.println("Categoria com ID: " + id + " não existe!");
        }
        else {
            repository.deletar(id);
        }
    }
}
