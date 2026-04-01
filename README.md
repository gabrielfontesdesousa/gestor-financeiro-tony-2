/*
========================================
CLASSE: ConexaoDB
PACOTE: db
========================================
Métodos:
- public static Connection getConexao()

Responsabilidade:
- Abrir e retornar uma conexão com o banco H2 em memória
*/


/*
========================================
CLASSE: InicializadorDB
PACOTE: db
========================================
Métodos:
- public static void inicializar()

Responsabilidade:
- Ler o arquivo schema.sql
- Executar os comandos SQL
- Criar as tabelas do banco
*/


/*
========================================
CLASSE: CategoriaRepository
PACOTE: repository
========================================
Métodos:
- public void salvar(Categoria categoria)
- public List<Categoria> listarTodas()
- public Categoria buscarPorId(Integer id)
- public List<Categoria> buscarPorNome(String nome)
- public void atualizar(Integer id, Categoria categoriaAtualizada)
- public void deletar(Integer id)

Responsabilidade:
- Fazer operações CRUD da tabela categoria
*/


/*
========================================
CLASSE: TransacaoRepository
PACOTE: repository
========================================
Métodos:
- public void salvar(Transacao transacao)
- public List<Transacao> listarTodas()
- public Transacao buscarPorId(Integer id)
- public List<Transacao> listarPorTipo(TipoTransacao tipo)
- public List<Transacao> listarPorCategoria(Integer categoriaId)
- public void atualizar(Integer id, Transacao transacaoAtualizada)
- public void deletar(Integer id)

Responsabilidade:
- Fazer operações CRUD da tabela transacao
- Filtrar por tipo
- Filtrar por categoria
*/


/*
========================================
CLASSE: LogTransactionRepository
PACOTE: repository
========================================
Métodos:
- public void salvar(LogTransacao logTransacao)
- public List<LogTransacao> listarTodos()
- public List<LogTransacao> listarPorStatus(StatusTransacao status)
- public List<LogTransacao> listarPorTransacao(Integer transacaoId)
- public void deletar(Integer id)

Responsabilidade:
- Registrar logs das operações
- Listar logs por status ou por transação
*/


/*
========================================
CLASSE: MetaRepository
PACOTE: repository
========================================
Métodos:
- public void salvar(Meta meta)
- public List<Meta> listarTodas()
- public Meta buscarPorId(Integer id)
- public void atualizar(Integer id, Meta metaAtualizada)
- public void atualizarProgresso(Integer id, Double novoValorAtual)
- public void deletar(Integer id)

Responsabilidade:
- Fazer operações CRUD da tabela meta
- Atualizar apenas o progresso (valorAtual)
*/


/*
========================================
CLASSE: CategoriaService
PACOTE: service
========================================
Métodos:
- public void cadastrarCategoria(Categoria categoria)
- public List<Categoria> listarCategorias()
- public Categoria buscarCategoriaPorId(Integer id)
- public List<Categoria> buscarCategoriaPorNome(String nome)
- public void atualizarCategoria(Integer id, Categoria categoria)
- public void removerCategoria(Integer id)

Responsabilidade:
- Regras de negócio relacionadas a Categoria
- Chamar CategoriaRepository
*/


/*
========================================
CLASSE: TransacaoService
PACOTE: service
========================================
Métodos:
- public void cadastrarTransacao(Transacao transacao)
- public List<Transacao> listarTransacoes()
- public Transacao buscarTransacaoPorId(Integer id)
- public List<Transacao> listarPorTipo(TipoTransacao tipo)
- public List<Transacao> listarPorCategoria(Integer categoriaId)
- public void atualizarTransacao(Integer id, Transacao transacao)
- public void removerTransacao(Integer id)

Responsabilidade:
- Regras de negócio de transações
- Validar tipo, categoria, valor, etc.
- Chamar TransacaoRepository
- (Opcional) registrar log automático ao salvar
*/


/*
========================================
CLASSE: LogTransacaoService
PACOTE: service
========================================
Métodos:
- public void registrarLog(LogTransacao log)
- public List<LogTransacao> listarLogs()
- public List<LogTransacao> listarLogsPorStatus(StatusTransacao status)
- public List<LogTransacao> listarLogsPorTransacao(Integer transacaoId)
- public void removerLog(Integer id)

Responsabilidade:
- Centralizar criação e consulta de logs
- Chamar LogTransactionRepository
*/


/*
========================================
CLASSE: MetaService
PACOTE: service
========================================
Métodos:
- public void cadastrarMeta(Meta meta)
- public List<Meta> listarMetas()
- public Meta buscarMetaPorId(Integer id)
- public void atualizarMeta(Integer id, Meta meta)
- public void atualizarProgressoMeta(Integer id, Double novoValorAtual)
- public void removerMeta(Integer id)

Responsabilidade:
- Regras de negócio de metas
- Validar valorMeta, valorAtual, dataLimite
- Chamar MetaRepository
*/


/*
========================================
CLASSE: MainDB
PACOTE: raiz do projeto
========================================
Fluxo sugerido de uso:
- InicializarDB.inicializar()
- Criar repositories/services
- Cadastrar categorias
- Listar categorias
- Buscar categoria por nome
- Cadastrar transações
- Listar transações
- Filtrar transações por tipo
- Cadastrar logs
- Listar logs
- Cadastrar metas
- Listar metas
- Atualizar progresso da meta
- Deletar registros (se quiser demonstrar)

Responsabilidade:
- Demonstrar o funcionamento do sistema inteiro
*/


/*
========================================
RESUMO TOTAL DOS MÉTODOS POR CAMADA
========================================

DB
- ConexaoDB.getConexao()
- InicializadorDB.inicializar()

REPOSITORY
CategoriaRepository:
- salvar()
- listarTodas()
- buscarPorId()
- buscarPorNome()
- atualizar()
- deletar()

TransacaoRepository:
- salvar()
- listarTodas()
- buscarPorId()
- listarPorTipo()
- listarPorCategoria()
- atualizar()
- deletar()

LogTransactionRepository:
- salvar()
- listarTodos()
- listarPorStatus()
- listarPorTransacao()
- deletar()
MetaRepository:
- salvar()
- listarTodas()
- buscarPorId()
- atualizar()
- atualizarProgresso()
- deletar()

SERVICE
CategoriaService:
- cadastrarCategoria()
- listarCategorias()
- buscarCategoriaPorId()
- buscarCategoriaPorNome()
- atualizarCategoria()
- removerCategoria()

TransacaoService:
- cadastrarTransacao()
- listarTransacoes()
- buscarTransacaoPorId()
- listarPorTipo()
- listarPorCategoria()
- atualizarTransacao()
- removerTransacao()

LogTransacaoService:
- registrarLog()
- listarLogs()
- listarLogsPorStatus()
- listarLogsPorTransacao()
- removerLog()

MetaService:
- cadastrarMeta()
- listarMetas()
- buscarMetaPorId()
- atualizarMeta()
- atualizarProgressoMeta()
- removerMeta()
*/
