# Partie analyse statistique : Ressource R2.08

## Initialisation 
Nous souhaitons analyser la relation entre le nombre de ventes de livres par mois (NbVentes)
réalisées et le chiffre d’affaires (CA) généré.

la première étape consiste à obtenir nos données via la requete
sql correspondante

     select sum(D.qte * prixvente) CA, sum(P.qte) qteStock, month(datecom) mois
     from DETAILCOMMANDE D
     natural join COMMANDE
     natural join CLIENT
     natural join MAGASIN
     natural join POSSEDER P
     where year(datecom) = 2024
     group by mois;

par soucis de manque de precision, nous allons prendre en compte les
donnée de 2024

Grace à cette requette nous obtenons les resultats suivants


| CA     | qteStock | mois |
|--------|----------|------|
|  14.00 |        1 |    1 |
|  18.00 |        2 |    3 |
|  35.00 |        2 |    4 |
|  36.00 |        4 |    5 |
|  10.00 |        1 |    7 |
|  33.56 |        3 |    8 |
| 176.30 |       10 |    9 |
| 168.57 |        6 |   10 |
|  33.27 |        4 |   11 |
| 232.14 |       13 |   12 |


## 1

grâce à la librairie matplot.lib de python nous allons pouvoir réaliser une analyse statistique

voici un graphique représentant la quantité en stock en fonction  le chiffre d'affaire 

![QS en f de CA](image/img1.png)

## 2

à partir de ce resultat, nous pouvons remarquer une tendance qui ce raporte à une droite passant par la majorité des points

## 3

en vue de notre hypothèse, nous allons essayer de la verifié en calculant le coefficient de correlation

soit 

$$P = \frac{Cov(XY)}{\sqrt{Var(X)*Var(Y)}} $$

ou

$$ Cov(XY) = \sum (X_i - \bar{X}) (Y_i - \bar{Y}) $$

et 

$$Var(X) = \sum (X_i - \bar{X})^2
$$

---

on obtient

$Var(CA) = 6148$

$Var(QS) = 14.44$

$Cov(CAQS) = 281.8536$

$P = 0.945960607698485$

$P > 0.8$ se qui signifie que les 2 jeux de donnée sont correler par une droite de régression linéaire $ax+b$



avec 

$a = \frac{Cov(XY)}{Var(X)} $

$b = \bar{X} - a\bar{Y}$

on obtient 

![alt text](image/img2.png)

## 4

en respectant la formule on peut estimez le nombre de ventes si le chiffre d’affaires est de 1250 euros

![alt text](image/img3.png)

on peut alors estimer que le stock doit avoisiner les 60 livres pour que le chiffre d'affaire soit de 1250 euro

## 5

il est important de preciser qu'il ne s'agit que d'une estimation, peut être qu'en augmentant le volume, les données peuvent varié ou ne plus être correler, il faudra alors prendre un autre modèle pour realiser des approximation plus precise 













