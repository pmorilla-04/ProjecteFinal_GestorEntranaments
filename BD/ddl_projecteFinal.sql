drop database if exists Entranaments;
create database Entranaments;
use Entranaments;

create table usuari (
	id int unsigned not null auto_increment, 
    nom varchar(50),
    contrassenya varchar (50),
    rol ENUM('ADMIN', 'ESPORTISTA', 'ENTRENADOR'),
    primary key(id)

);

create table tipus_esport(
	id int unsigned not null auto_increment,
    nom varchar(50),
    
     primary key(id)
);

create table entrenament (
	id int unsigned not null auto_increment, 
    `data` date,
    duradaMinuts int,
    distancia int,
    descripcio varchar (200),
    intensitat ENUM ("BAIXA", "MITJA", "ALTA"),
    completat boolean,
    validar boolean,
    usuari_id int unsigned not null,
    tipus_esport_id int unsigned not null,
    
    primary key(id),
    foreign key(usuari_id) references usuari(id),
    foreign key(tipus_esport_id) references tipus_esport(id)
);



create table comentari(
	id int unsigned not null auto_increment,
    text varchar(200),
    `data` date,
    entranador_id int unsigned not null,
    entranament_id int unsigned not null,
    
    primary key (id),

    foreign key(entranador_id) 
        references usuari(id)
        on delete cascade,

    foreign key(entranament_id) 
        references entrenament(id)
        on delete cascade
);




