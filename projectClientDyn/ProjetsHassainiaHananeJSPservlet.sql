-- Projet web dynamique 

--  1 : Création de la bdd
create database GestionCompteDynamique;

-- 2 : Selection la base de données dans laquelle on veut travailler 
use GestionCompteDynamique;

-- 2 : Création des tables : CompteCourant, CompteEpargne, Client

-- table Conseiller
create table conseiller(
id_c int primary key auto_increment,
nom_c varchar(50), 
prenom_c varchar(50),
adresse_c varchar(70),
codePostal_c long, 
ville_c varchar(50),
telephone_c varchar(10), 
mail_c varchar(50), 
motDePasse_c varchar(50)
); 

	-- table Client
create table client(
id int primary key auto_increment,
nom varchar(50), 
prenom  varchar(50),
Birth date,
adresse varchar(70),
codePostal long, 
ville varchar(50),
telephone varchar(10), 
mail varchar(50), 
motDePasse varchar(50), 
id_c int, 
foreign key(id_c) references conseiller (id_c)
); 

	-- table CompteCourant
create table compteCourant(
id_cc int primary key auto_increment,
solde_cc double,
decouvert double default 100.00,
id_clt int,
foreign key(id_clt) references client (id) ON DELETE CASCADE
); 

-- table CompteEpargne
create table compteEpargne(
id_ce int primary key auto_increment,
dateCe DATETIME DEFAULT CURRENT_TIMESTAMP,
solde_ce double,
tauxRemuneration double default 0.03,
id_clt int,
foreign key(id_clt) references client (id) ON DELETE CASCADE
); 

-- table Opérations cc
create table operation(
id_op int primary key auto_increment,
typeCpt varchar(50),
date_op DATETIME DEFAULT CURRENT_TIMESTAMP,
operation varchar(50),
montant_op double,
id_c int,
foreign key(id_cptC) references comptecourant (id_cc) ON DELETE CASCADE
); 


-- table Opérations ce
create table operationCe(
id_op int primary key auto_increment,
typeCpt varchar(50),
date_op DATETIME DEFAULT CURRENT_TIMESTAMP,
operation varchar(50),
montant_op double,
id_c int,
foreign key(id_cptC) references comptecourant (id_ce) ON DELETE CASCADE
); 