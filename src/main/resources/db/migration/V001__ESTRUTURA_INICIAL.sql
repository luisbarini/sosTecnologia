CREATE TABLE marca (
	id bigint(20) auto_increment NOT NULL,
	descricao varchar(2000) NOT NULL,
	CONSTRAINT marca_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

CREATE UNIQUE INDEX marca_id_IDX USING BTREE ON marca (id);
CREATE INDEX marca_descricao_IDX USING BTREE ON marca (descricao);

CREATE TABLE patrimonio (
	id BIGINT(20) auto_increment NOT NULL,
	descricao varchar(2000) NOT NULL,
	tombo int(10) NOT NULL,
	nome varchar(100) NOT NULL,
	marca_id BIGINT(20) NOT NULL,
	CONSTRAINT patrimonio_pk PRIMARY KEY (id),
	CONSTRAINT marca_FK FOREIGN KEY (marca_id) REFERENCES marca(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

CREATE UNIQUE INDEX patrimonio_id_IDX USING BTREE ON patrimonio (id);
CREATE UNIQUE INDEX patrimonio_tombo_IDX USING BTREE ON patrimonio (tombo);
CREATE INDEX patrimonio_nome_IDX USING BTREE ON patrimonio (nome);