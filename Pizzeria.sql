drop table if exists PizzaBase cascade;
drop table if exists IngredienteAdicional cascade;
drop table if exists Carne cascade;
drop table if exists Vegetal cascade;
drop table if exists Salsa cascade;
drop table if exists itemPedido cascade;
drop table if exists Factura cascade;

/*		INTEGRANTES		
	Aurelio Antonio Vivas Mesa
	Bryan Stiven Tabarez Mestra
	Eduardo Saavedra Perafan
	George Steeven Romero Ramirez
*/

create table PizzaBase (
	pizza_id serial primary key,
	nombre   varchar(50) not null,
	tamanio varchar(20) not null,
	presentacion  varchar(20) not null,
	precio integer not null,
	foto bytea
);

create table IngredienteAdicional (
	ingrediente_id serial primary key,
	nombre   varchar(50) not null,
	precioPorcion integer not null,
	cantidad  integer not null,
	tipo varchar(20) not null,
	foto bytea
);
 

create table Carne (
	carne_id serial primary key,
	presentacion   varchar(50) not null,
	cantidadGrasas varchar(10) not null,
	animal varchar(20) not null

) INHERITS (IngredienteAdicional);


create table Vegetal (
	vegetal_id serial primary key,
	carbohidratos   varchar(50) not null
) INHERITS (IngredienteAdicional);


create table Salsa (
	salsa_id serial primary key,
	azucares   varchar(10) not null,
	carbohidratos   varchar(10) not null,
	grasa   varchar(10) not null
) INHERITS (IngredienteAdicional);


create table Factura (
	factura_id serial primary key,
	fecha  timestamp  not null,
	precioTotal integer not null
);


create table itemPedido (
	factura_id integer not null,
	pizza_id  integer  not null,
	ingrediente_id integer not null,

	constraint factura_fk foreign key (factura_id)
      REFERENCES Factura (factura_id),

	constraint pizza_fk foreign key (pizza_id)
      REFERENCES PizzaBase (pizza_id),

	constraint ingrediente_fk foreign key (ingrediente_id)
      REFERENCES IngredienteAdicional (ingrediente_id),

	constraint itempedido_pk PRIMARY KEY (pizza_id, ingrediente_id)
);

