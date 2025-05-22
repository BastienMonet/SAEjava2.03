Bastien Monet 

# Demonstration de compétence

Lors de cette SAE j'ai participé à la complétion du fichier sae2025.sql
J'ai réaliser les graphiques sur libreoffice base et j'ai compléter le fichier statistique


Difficulté rencontré:

### sqlAlchemy import not found

je suis tombé sur un ordi ou les packages python sur les ordinateur ne pouvait être gérer que de manière externe.
j'ai donc dû creer une venv pour installer sqlalchemy


python -m venv venv
source venv/bin/activate
pip install mysql-connector-python sqlalchemy pymsql

ce problème ne m'est arriver qu'une seul fois


### je ne connaissais pas libre office Base

vu que les tp, td arrives au goute à goute, je n'avais aucune idée de comment transformer
mes requetes sql en graphiques. Mon objectif principale était alors dans un premier temps de
transformer mes requetes en csv, car je sais de mes cours que le csv est importable sur libreoffice calc. 

J'ai réussi à trouver se que je voulais au bout de quelque temps (le fichier sql_to_csv) mqlAlchemy et la fonction read_sql de la librairie panda

au final ça na pas servi à grand chose vu que libre office base est plus pratique (mais passer par du csv marche aussi)

### je ne savais pas comment faire des formules mathématiques sur un markdown

mais bon, google existe

