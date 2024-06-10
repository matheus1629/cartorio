CREATE TABLE cartorio (
    id INT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    observacao VARCHAR(250),
    situacao_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    CHECK (id > 0),
    CONSTRAINT fk_situacao
        FOREIGN KEY (situacao_id)
        REFERENCES situacao (id)
);