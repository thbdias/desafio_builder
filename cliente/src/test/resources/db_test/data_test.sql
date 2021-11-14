CREATE TABLE cliente (
	id int4 NOT NULL,
	data_nascimento date NOT NULL,
	nome varchar(255) NOT NULL,
	numero_registro int4 NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

insert into cliente 
values(1, '2011-12-31', 'jose', 2);

insert into cliente
values(2, '2011-12-10', 'maria', 3);

insert into cliente
values(7, '2010-01-11', 'pedro', 3);

insert into cliente
values(8, '2010-01-10', 'jose santos', 1);

insert into cliente
values(9, '2010-01-10', 'jose santos pedro', 1);

insert into cliente
values(10, '2010-01-10', 'jose santos pedro mateus', 1);

insert into cliente
values(11, '2010-01-10', 'andreia jose', 5);

insert into cliente
values(12, '2010-01-10', 'andreia jose maria', 5);

insert into cliente
values(13, '2010-01-10', 'jose maria', 5);

insert into cliente
values(14, '2010-01-10', 'helena', 5);

insert into cliente
values(15, '2010-01-10', 'antonio marcos', 6);
