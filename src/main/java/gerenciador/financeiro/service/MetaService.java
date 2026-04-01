package gerenciador.financeiro.service;

import gerenciador.financeiro.model.Meta;
import gerenciador.financeiro.repository.MetaRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;

public class MetaService {
    private final MetaRepository metaRepository;


    public MetaService(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    public void cadastrarMeta(Meta meta){
        if (meta.getValorMeta() <= 0){
            throw new RuntimeException("A meta não pode ser um valor negativo ou igual a zero");
        }
        if (meta.getValorAtual() < 0){
            throw new RuntimeException("O valor atual não pode ser negativo");
        }
        if (meta.getDataLimite().isBefore(LocalDate.now())){
            throw new RuntimeException("Data deve ser futura!");
        }
        metaRepository.salvar(meta);
    }

    public List<Meta> listarMetas(){
        if (metaRepository.listarTodas().isEmpty()){
            throw new RuntimeException("Não existem metas cadastradas");
        }
        return metaRepository.listarTodas();
    }

    public Meta buscarMetaPorId(Integer id){
        try{
            return metaRepository.buscarPorId(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Meta com o ID: " + id + " não existe!");
        }
    }

    public void atualizarMeta(Integer id, Meta meta){
        if (meta.getValorMeta() <= meta.getValorAtual()){
            throw new RuntimeException("Meta não pode ser inferior ou igual ao valor atual!");
        }
        if (meta.getDataLimite().isBefore(LocalDate.now())){
            throw new RuntimeException("Data deve ser futura!");
        }
        metaRepository.atualizar(id, meta);
        System.out.println("Meta foi atualizada!");
    }

    public void atualizarProgressoMeta(Integer id, Double novoValorAtual){
        if (novoValorAtual < 0 ){
            throw new RuntimeException("Valor não pode ser menor do que 0 - (zero)");
        }
        metaRepository.atualizarProgresso(id, novoValorAtual);
    }

    public void removerMeta(Integer id){
        try{
            metaRepository.deletar(id);
            System.out.println("Meta com ID: " + id + " foi removida!");
        }
        catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Meta com ID: " + id + " não existe.");
        }
    }
}
