python facture.py --serveur servinfo-maria --bd DBmonet --login monet


python -m venv venv
source venv/bin/activate
pip install mysql-connector-python sqlalchemy pymsql






difficulté :

Graphique 8 sans vue pour sqlalchemy

select annee, max(ma) as maximum, min(mi) as minimum, round(avg(mo),2) as moyenne
from
    (select year(datecom) as annee, sum(qte * prixvente) as ma, sum(qte * prixvente) as mi, sum(qte * prixvente) as mo
    from CLIENT 
    natural join COMMANDE 
    natural join DETAILCOMMANDE
    group by annee, idcli) as G
group by annee;



This issue occurs because you’re using code which was written for SQLAlchemy 1.x, but you have installed SQLAlchemy 2.x. Fortunately. this is rather easy to fix:


from sqlalchemy.sql import text

-28  return self.cnx.execute(text(requete))