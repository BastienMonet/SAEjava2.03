-- Devoir 127
-- Nom: , Prenom: 

-- Feuille SAE2.05 Exploitation d'une base de données: Livre Express
-- 
-- Veillez à bien répondre aux emplacements indiqués.
-- Seule la première requête est prise en compte.

-- +-----------------------+--
-- * Question 127156 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Quels sont les livres qui ont été commandés le 1er décembre 2024 ?

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +---------------+--------------------------------------------+---------+-----------+-------+
-- | isbn          | titre                                      | nbpages | datepubli | prix  |
-- +---------------+--------------------------------------------+---------+-----------+-------+
-- | etc...
-- = Reponse question 127156.

select isbn, titre, ifnull(nbpages,0) nbpages, datepubli, prix
from LIVRE
natural join DETAILCOMMANDE
natural join COMMANDE
where DAY(datecom) = 1 and Month(datecom) = 12 and Year(datecom) = 2024;
-- ok


-- +-----------------------+--
-- * Question 127202 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Quels clients ont commandé des livres de René Goscinny en 2021 ?

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------+---------+-----------+-----------------------------+------------+-------------+
-- | idcli | nomcli  | prenomcli | adressecli                  | codepostal | villecli    |
-- +-------+---------+-----------+-----------------------------+------------+-------------+
-- | etc...
-- = Reponse question 127202.
select distinct idcli, nomcli, prenomcli, adressecli, codepostal, villecli
from CLIENT 
natural join COMMANDE 
natural join DETAILCOMMANDE 
natural join LIVRE 
natural join AUTEUR
where Year(datecom) = 2021 and nomauteur = 'René Goscinny';
-- ok

-- +-----------------------+--
-- * Question 127235 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Quels sont les livres sans auteur et étant en stock dans au moins un magasin en quantité strictement supérieure à 8 ?

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +---------------+-----------------------------------+-------------------------+-----+
-- | isbn          | titre                             | nommag                  | qte |
-- +---------------+-----------------------------------+-------------------------+-----+
-- | etc...
-- = Reponse question 127235.
select isbn, titre, nommag, qte
from LIVRE 
natural join POSSEDER 
natural join MAGASIN
where qte > 8 and isbn not in (select isbn from ECRIRE);
-- ok

-- +-----------------------+--
-- * Question 127279 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Pour chaque magasin, on veut le nombre de clients qui habitent dans la ville de ce magasin (en affichant les 0)

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------+-------------------------+-------+
-- | idmag | nommag                  | nbcli |
-- +-------+-------------------------+-------+
-- | etc...
-- = Reponse question 127279.
select idmag, nommag, ifNull(count(idcli),0) nbcli
from MAGASIN 
natural left join COMMANDE
group by idmag;
-- ok


-- +-----------------------+--
-- * Question 127291 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Pour chaque magasin, on veut la quantité de livres achetés le 15/09/2022 en affichant les 0.

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------------------------+------+
-- | nommag                  | nbex |
-- +-------------------------+------+
-- | etc...
-- = Reponse question 127291.
select nommag, ifNull(count(qte),0) nbex
from MAGASIN 
natural left join COMMANDE 
natural join DETAILCOMMANDE
where day(datecom) = 15 and month(datecom) = 9 and year(datecom) = 2022
group by nommag;
-- pas ok

-- where str_to_date(datecom,'%d/%m/%Y') = '15/9/2022'




-- +-----------------------+--
-- * Question 127314 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Instructions d'insertion dans la base de données

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +------------+
-- | insertions |
-- +------------+
-- | etc...
-- = Reponse question 127314.
insert into LIVRE values ('9782844273765', 'SQL pour les Nuls', 292, 2002, 33.50);

insert into AUTEUR values ('OL246259A', 'Allen G. Taylor', null, null);
insert into AUTEUR values ('OL7670824A', 'Reinhard Engel', null, null);

insert into ECRIRE values ('9782844273765', 'OL246259A');
insert into ECRIRE values ('9782844273765', 'OL7670824A');

insert into POSSEDER values (7,'9782844273765',3);
-- ok


-- +-----------------------+--
-- * Question 127369 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 1 Nombre de livres vendus par magasin et par an

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------------------------+-------+-----+
-- | Magasin                 | Année | qte |
-- +-------------------------+-------+-----+
-- | etc...
-- = Reponse question 127369.

select distinct nommag as Magasin, year(datecom) Année, sum(qte) qte
from COMMANDE 
natural join MAGASIN
natural join DETAILCOMMANDE
group by Année, idmag;
-- ok


-- +-----------------------+--
-- * Question 127370 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 2  Chiffre d'affaire par thème en 2024

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +--------------------------------------+---------+
-- | Theme                                | Montant |
-- +--------------------------------------+---------+
-- | etc...
-- = Reponse question 127370.
Create or replace view Graphique2 as (
    select nomclass, iddewey, sum(prixvente * qte) as MontantParId100
    from LIVRE 
    natural join DETAILCOMMANDE 
    natural join COMMANDE 
    natural join THEMES
    natural join CLASSIFICATION
    where YEAR(datecom) = 2024 and iddewey in (select iddewey from CLASSIFICATION where iddewey % 100 = 0)
    group by iddewey);
    -- ok
    
create or replace view CAtotal as (
    select sum(MontantParId100) CAtotal from Graphique2);
-- ok

select nomclass, MontantParId100 / CAtotal * 100
from Graphique2
cross join CAtotal;
-- ok




-- +-----------------------+--
-- * Question 127381 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 3 Evolution chiffre d'affaire par magasin et par mois en 2024

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +------+-------------------------+---------+
-- | mois | Magasin                 | CA      |
-- +------+-------------------------+---------+
-- | etc...
-- = Reponse question 127381.
select MONTH(datecom) mois ,nommag, sum(prixvente * qte) CA
from MAGASIN 
natural join DETAILCOMMANDE 
natural join COMMANDE
where YEAR(datecom) = 2024
group by nommag, MONTH(datecom);
-- ok


-- +-----------------------+--
-- * Question 127437 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 4 Comparaison ventes en ligne et ventes en magasin

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------+------------+---------+
-- | annee | typevente  | montant |
-- +-------+------------+---------+
-- | etc...
-- = Reponse question 127437.
select Year(datecom) annee, enligne as typevente, round(sum(qte * prixvente)) montant
from COMMANDE 
natural join DETAILCOMMANDE 
natural join MAGASIN 
where Year(datecom) != 2025
group by enligne, annee;
-- ok


-- +-----------------------+--
-- * Question 127471 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 5 Les dix éditeurs les plus importants en nombre d'auteurs

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------------------+-----------+
-- | Editeur           | nbauteurs |
-- +-------------------+-----------+
-- | etc...
-- = Reponse question 127471.
select nomedit as Editeur , count(idauteur) nbauteurs
from EDITEUR 
natural join EDITER 
natural join LIVRE 
natural join ECRIRE
group by Editeur
order by nbauteurs desc
limit 10;
-- ok


-- +-----------------------+--
-- * Question 127516 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 6 Qté de livres de R. Goscinny achetés en fonction de l'orgine des clients

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------------+-----+
-- | ville       | qte |
-- +-------------+-----+
-- | etc...
-- = Reponse question 127516.
select villecli as ville, sum(qte) qte
from CLIENT 
natural join COMMANDE 
natural join DETAILCOMMANDE 
natural join LIVRE 
natural join AUTEUR 
natural join ECRIRE
where nomauteur = 'René Goscinny'
group by ville;
-- ok


-- +-----------------------+--
-- * Question 127527 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Graphique 7 Valeur du stock par magasin
-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------------------------+---------+
-- | Magasin                 | total   |
-- +-------------------------+---------+
-- | etc...
-- = Reponse question 127527.
select nommag Magasin, sum(qte * prix) total
from MAGASIN 
natural join POSSEDER 
natural join LIVRE
group by nommag;
-- ok


-- +-----------------------+--
-- * Question 127538 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
-- Requête Graphique 8 Statistiques sur l'évolution du chiffre d'affaire total par client 
-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------+---------+---------+---------+
-- | annee | maximum | minimum | moyenne |
-- +-------+---------+---------+---------+
-- | etc...
-- = Reponse question 127538.
Create or replace view Graphique8 as (
    select year(datecom) as annee, sum(qte * prixvente) CA
    from CLIENT 
    natural join COMMANDE 
    natural join DETAILCOMMANDE
    group by annee, idcli);


select annee, max(CA) as maximum, min(CA) as minimum, round(avg(CA),2) as moyenne
from Graphique8
group by annee;
-- ok

-- +-----------------------+--
-- * Question 127572 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête Palmarès

-- Voici le début de ce que vous devez obtenir.
-- ATTENTION à l'ordre des colonnes et leur nom!
-- +-------+-----------------------+-------+
-- | annee | nomauteur             | total |
-- +-------+-----------------------+-------+
-- | etc...
-- = Reponse question 127572.
Create or replace view Palmares as (
    select year(datecom) annee, idauteur, nomauteur, sum(qte) qteVendu
    from AUTEUR 
    natural join ECRIRE 
    natural join LIVRE 
    natural join DETAILCOMMANDE 
    natural join COMMANDE 
    where year(datecom) != 2025
    group by annee , idauteur
    order by annee, qteVendu desc);

select annee, nomauteur, max(qteVendu) total
from Palmares 
group by annee;



-- +-----------------------+--
-- * Question 127574 : 2pts --
-- +-----------------------+--
-- Ecrire une requête qui renvoie les informations suivantes:
--  Requête imprimer les commandes en considérant que l'on veut celles de février 2020
-- = Reponse question 127574

select idcli, nomcli, prenomcli, adressecli, isbn, titre, prixvente, qte, qte * prixvente total
from CLIENT
natural join COMMANDE
natural join DETAILCOMMANDE
natural join LIVRE

-- select idcli ,sum(total)
-- from CommandeCli
-- group by idcli;


