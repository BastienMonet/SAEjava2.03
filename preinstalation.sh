
#!/bin/bash

# Demander les paramètres à l'utilisateur
read -p "Hôte de la base de données (servinfo-maria par défaut sinon tappé localhost) : " DB_HOST
DB_HOST=${DB_HOST:-servinfo-maria}

read -p "Nom d'utilisateur : " DB_USER
read -s -p "Mot de passe : " DB_PASS
echo ""
read -p "Nom de la base de données : " DB_NAME

mysql -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" <<EOF
source DB/sujet/destructionLivreExpress.sql
source DB/sujet/creationLivreExpress.sql
EOF

# pre installation

cd sae
mvn compile 
mvn exec:java -Dexec.mainClass="fr.saejava.modele.ExecutablePreTest"

