DROP TABLE IF EXISTS log_transacao;
DROP TABLE IF EXISTS transacao;
DROP TABLE IF EXISTS meta;
DROP TABLE IF EXISTS categoria;

CREATE TABLE categoria
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE transacao
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    valor        DECIMAL(10, 2) NOT NULL,
    data_hora    TIMESTAMP,
    descricao    VARCHAR(255)   NOT NULL,
    tipo         VARCHAR(20)    NOT NULL,
    status       VARCHAR(20)    NOT NULL,
    categoria_id INT,
    CONSTRAINT fk_transacao_categoria
        FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);

CREATE TABLE log_transacao
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    data_hora    TIMESTAMP    NOT NULL,
    mensagem     VARCHAR(255) NOT NULL,
    status       VARCHAR(30)  NOT NULL,
    transacao_id INT,
    CONSTRAINT fk_log_transacao
        FOREIGN KEY (transacao_id) REFERENCES transacao (id)
);

CREATE TABLE meta
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    valor_meta  DECIMAL(10, 2) NOT NULL,
    valor_atual DECIMAL(10, 2) NOT NULL,
    data_limite DATE
);
