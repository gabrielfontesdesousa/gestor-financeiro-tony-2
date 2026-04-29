package gerenciador.financeiro;

import gerenciador.financeiro.db.ConexaoDB;
import gerenciador.financeiro.db.InicializadorDB;
import gerenciador.financeiro.enums.TipoTransacao;
import gerenciador.financeiro.exception.ValidationException;
import gerenciador.financeiro.model.Categoria;
import gerenciador.financeiro.model.Meta;
import gerenciador.financeiro.model.Transacao;
import gerenciador.financeiro.repository.CategoriaRepository;
import gerenciador.financeiro.repository.MetaRepository;
import gerenciador.financeiro.repository.TransacaoRepository;
import gerenciador.financeiro.service.CategoriaService;
import gerenciador.financeiro.service.MetaService;
import gerenciador.financeiro.service.TransacaoService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner leitor = new Scanner(System.in);
    static ConexaoDB conexaoDB = new ConexaoDB();

    static TransacaoService transacaoService = new TransacaoService(new TransacaoRepository(conexaoDB.getJdbcTemplate()));

    static CategoriaService categoriaService = new CategoriaService(new CategoriaRepository(conexaoDB.getJdbcTemplate()));
    static MetaService metaService = new MetaService(new MetaRepository(conexaoDB.getJdbcTemplate()));


    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        InicializadorDB.inicializar(conexaoDB);
        menuPrincipal();
    }

    public static void menuPrincipal() {
        boolean executando = true;

        while (executando) {
            limparConsole();
            System.out.println("=================================");
            System.out.println("     GERENCIADOR FINANCEIRO");
            System.out.println("=================================");
            System.out.println("1 - Categorias");
            System.out.println("2 - Transações");
            System.out.println("3 - Metas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                int opcao = Integer.parseInt(leitor.nextLine());


                switch (opcao) {
                    case 1:
                        telaCategorias();
                        break;
                    case 2:
                        telaTransacoes();
                        break;
                    case 3:
                        telaMetas();
                        break;
                    case 0:
                        executando = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        pausar();
                }
            } catch (NumberFormatException n) {
                System.out.println("Insira uma opção válida");
                pausar();
            }
        }
    }

    public static void telaCategorias() {
        boolean voltar = false;

        while (!voltar) {
            try {
                limparConsole();
                System.out.println("=================================");
                System.out.println("         MENU CATEGORIAS");
                System.out.println("=================================");
                System.out.println("1 - Cadastrar categoria");
                System.out.println("2 - Listar categorias");
                System.out.println("3 - Buscar por id");
                System.out.println("4 - Buscar por nome");
                System.out.println("5 - Atualizar categoria");
                System.out.println("6 - Deletar categoria");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");

                int opcao = leitor.nextInt();
                leitor.nextLine();


                switch (opcao) {
                    case 1:
                        System.out.println(">>> Cadastrar categoria");
                        System.out.println("Nome da categoria:");
                        String nome = leitor.nextLine();
                        System.out.println("Descrição: ");
                        String desc = leitor.nextLine();
                        Categoria categoria = new Categoria(nome, desc);
                        categoriaService.cadastrarCategoria(categoria);
                        System.out.println("Categoria registrada com sucesso!");
                        pausar();
                        break;
                    case 2:
                        System.out.println(">>> Listar categorias");
                        categoriaService.listarCategorias().forEach(c -> {
                            System.out.println("ID: " + c.getId());
                            System.out.println("Nome: " + c.getNome());
                            System.out.println("Descrição: " + c.getDescricao());
                            System.out.println("------------------");
                        });
                        pausar();
                        break;
                    case 3:
                        System.out.println(">>> Buscar categoria por id");
                        System.out.println("Informe o id que deseja buscar: ");
                        Integer id = leitor.nextInt();
                        leitor.nextLine();
                        categoria = categoriaService.buscarCategoriaPorId(id);
                        System.out.println(categoria.toString());
                        pausar();
                        break;
                    case 4:
                        System.out.println(">>> Buscar categoria por nome");
                        System.out.println("Informe o nome da categoria: ");
                        String buscarNome = leitor.nextLine();
                        categoria = categoriaService.buscarCategoriaPorNome(buscarNome);
                        System.out.println(categoria.toString());
                        pausar();
                        break;
                    case 5:
                        System.out.println(">>> Atualizar categoria");
                        System.out.println("Informe o ID da categoria a ser atualizada: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                        System.out.println("Novo nome:");
                        String novoNome = leitor.nextLine();
                        System.out.println("Nova descrição:");
                        String novaDescricao = leitor.nextLine();
                        categoria = categoriaService.buscarCategoriaPorId(id);
                        categoria.setNome(novoNome);
                        categoria.setDescricao(novaDescricao);
                        categoriaService.atualizarCategoria(id, categoria);
                        System.out.println(categoria.toString());
                        System.out.println("Categoria atualizada!");
                        pausar();
                        break;
                    case 6:
                        System.out.println(">>> Deletar categoria");
                        System.out.println("Informe o ID da categoria a ser deletada: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                        categoriaService.removerCategoria(id);
                        System.out.println("Categoria removida!");
                        pausar();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        pausar();
                }
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
                pausar();
            } catch (InputMismatchException i) {
                System.out.println("Insira uma opcão válida");
                leitor.nextLine();
                pausar();
            } catch (RuntimeException em) {
                System.out.println(em.getMessage());
                pausar();
            }
        }
    }

    public static void telaTransacoes() {
        boolean voltar = false;

        while (!voltar) {
            limparConsole();
            System.out.println("=================================");
            System.out.println("         MENU TRANSAÇÕES");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar transação");
            System.out.println("2 - Listar transações");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Listar por tipo");
            System.out.println("5 - Deletar transação");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            try {

                int opcao = leitor.nextInt();
                leitor.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println(">>> Cadastrar transação");


                        System.out.print("Valor da transação: ");
                        Double valor = leitor.nextDouble();
                        leitor.nextLine();


                        System.out.print("Descrição: ");
                        String descricao = leitor.nextLine();


                        System.out.println("Tipo da transação:");
                        System.out.println("  1 - Receita");
                        System.out.println("  2 - Despesa");
                        System.out.print("Escolha: ");
                        int tipoOpcao = leitor.nextInt();
                        leitor.nextLine();
                        String tipo = (tipoOpcao == 1) ? "RECEITA" : "DESPESA";

                        LocalDateTime dataHora = LocalDateTime.now();

                        System.out.println("\nCategorias disponíveis:");
                        List<Categoria> categorias = categoriaService.listarCategorias();

                        if (categorias == null || categorias.isEmpty()) {
                            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria primeiro.");
                            pausar();
                            break;
                        }

                        categorias.forEach(c -> System.out.println("  [" + c.getId() + "] " + c.getNome() + " - " + c.getDescricao()));

                        System.out.print("Informe o ID da categoria: ");
                        Integer categoriaId = leitor.nextInt();
                        leitor.nextLine();


                        Categoria categoriaSelecionada = categoriaService.buscarCategoriaPorId(categoriaId);
                        if (categoriaSelecionada == null) {
                            System.out.println("Categoria não encontrada. Transação não cadastrada.");
                            pausar();
                            break;
                        }


                        Transacao transacao = new Transacao(valor, dataHora, descricao, tipo, categoriaId);
                        transacaoService.cadastrarTransacao(transacao);
                        pausar();
                        break;

                    case 2:
                        System.out.println(">>> Listar transações");
                        List<Transacao> lista = transacaoService.listarTransacoes();
                        if (lista == null || lista.isEmpty()) {
                            System.out.println("Nenhuma transação cadastrada.");
                        } else {
                            lista.forEach(t -> {
                                System.out.println("ID: " + t.getId());
                                System.out.println("Valor: R$ " + t.getValor());
                                System.out.println("Data/Hora: " + (t.getDataHora() != null ? t.getDataHora().format(formatter) : "não informada"));
                                System.out.println("Descrição: " + t.getDescricao());
                                System.out.println("Tipo: " + t.getTipo());
                                System.out.println("Status: " + t.getStatus());
                                System.out.println("Categoria ID: " + t.getCategoriaId());
                                System.out.println("------------------");
                            });
                        }
                        pausar();
                        break;

                    case 3:
                        System.out.println(">>> Buscar transação por ID");
                        System.out.print("Informe o ID: ");
                        Integer id = leitor.nextInt();
                        leitor.nextLine();
                        Transacao encontrada = transacaoService.buscarTransacaoPorId(id);
                        if (encontrada != null) {
                            System.out.println("ID: " + encontrada.getId());
                            System.out.println("Valor: R$ " + encontrada.getValor());
                            System.out.println("Data/Hora: " + (encontrada.getDataHora() != null ? encontrada.getDataHora().format(formatter) : "não informada"));
                            System.out.println("Descrição: " + encontrada.getDescricao());
                            System.out.println("Tipo: " + encontrada.getTipo());
                            System.out.println("Categoria ID: " + encontrada.getCategoriaId());
                        } else {
                            System.out.println("Transação não encontrada.");
                        }
                        pausar();
                        break;

                    case 4:
                        System.out.println(">>> Listar transações por tipo");
                        System.out.println("  1 - Receita  |  2 - Despesa");
                        System.out.print("Escolha: ");
                        int filtroOpcao = leitor.nextInt();
                        leitor.nextLine();
                        String filtro = (filtroOpcao == 1) ? "Receita" : "Despesa";
                        System.out.println(transacaoService.listarPorTipo(filtro));
                        pausar();
                        break;

                    case 5:
                        System.out.println(">>> Deletar transação");
                        System.out.print("Informe o ID: ");
                        Integer idDel = leitor.nextInt();
                        leitor.nextLine();
                        transacaoService.removerTransacao(idDel);
                        pausar();
                        break;

                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        pausar();
                }
            } catch (InputMismatchException e) {
                System.out.println("Insira uma opcão válida");
                leitor.nextLine();
                pausar();
            }
        }

    }

    public static void telaMetas() {
        boolean voltar = false;

        while (!voltar) {
            limparConsole();
            System.out.println("=================================");
            System.out.println("           MENU METAS");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar meta");
            System.out.println("2 - Listar metas");
            System.out.println("3 - Atualizar meta");
            System.out.println("4 - Atualizar Progresso");
            System.out.println("5 - Deletar meta");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            try {

                int opcao = leitor.nextInt();
                leitor.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println(">>> Cadastrar meta");

                        System.out.print("Valor de Meta: ");
                        Double valorFinal = leitor.nextDouble();
                        leitor.nextLine();

                        System.out.print("Valor Atual: ");
                        Double valorAtual = leitor.nextDouble();
                        leitor.nextLine();

                        System.out.print("Data limite (dd/MM/yyyy): ");
                        String dataInput = leitor.nextLine();

                        LocalDate dataLimite;

                        try {
                            dataLimite = LocalDate.parse(dataInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Use dd/MM/yyyy");
                            pausar();
                            break;
                        }

                        Meta meta = new Meta(valorFinal, valorAtual, dataLimite);
                        metaService.cadastrarMeta(meta);

                        System.out.println("Sua meta foi registrada com sucesso!");
                        pausar();
                        break;
                    case 2:
                        System.out.println(">>> Listar metas");
                        metaService.listarMetas().forEach(m -> {
                            System.out.println("Valor da Meta: " + m.getValorMeta());
                            System.out.println("Valor Atual: " + m.getValorAtual());
                            System.out.println("Data Limite: " + m.getDataLimite());
                            System.out.println("------------------");
                        });
                        pausar();
                        break;
                    case 3:
                        System.out.println(">>> Atualizar meta");
                        System.out.println("Informe o ID da meta a ser atualizada: ");
                        int id = leitor.nextInt();
                        leitor.nextLine();
                        System.out.println("Novo valor da meta:");
                        Double novaMeta = leitor.nextDouble();
                        leitor.nextLine();
                        meta = metaService.buscarMetaPorId(id);
                        meta.setValorMeta(novaMeta);
                        metaService.atualizarMeta(id, meta);
                        System.out.println(meta.toString());
                        System.out.println("Meta foi atualizada");
                        pausar();
                        break;
                    case 4:
                        System.out.println(">>> Atualizar Progresso");
                        System.out.println("Informe o ID da meta a ser atualizada: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                        System.out.println("Novo valor atual:");
                        Double novoValor = leitor.nextDouble();
                        leitor.nextLine();
                        meta = metaService.buscarMetaPorId(id);
                        meta.setValorAtual(novoValor);
                        metaService.atualizarProgressoMeta(id, novoValor);
                        System.out.println(meta);
                        System.out.println("Meta foi atualizada");
                        pausar();
                        break;
                    case 5:
                        System.out.println(">>> Deletar meta");
                        System.out.println("Informe o ID da meta a ser deletada: ");
                        id = leitor.nextInt();
                        leitor.nextLine();
                        metaService.removerMeta(id);
                        System.out.println("Meta removida!");
                        pausar();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        pausar();
                }
            }  catch (InputMismatchException e) {
                System.out.println("Valor digitado Inválido para o campo. Apenas numeros são Aceitos!");
                leitor.nextLine();
                pausar();
            } catch (ValidationException v){
                System.out.println(v.getMessage());
                pausar();
            } catch (RuntimeException r){
                System.out.println(r.getMessage());
                pausar();
            }
        }
    }


    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        leitor.nextLine();
    }

    public static void limparConsole() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
