CREATE TABLE atribuicao
(
    id       VARCHAR(20) NOT NULL,
    nome     VARCHAR(50) NOT NULL,
    situacao BOOLEAN     NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);