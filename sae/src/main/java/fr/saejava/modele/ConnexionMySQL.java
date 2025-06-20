package fr.saejava.modele;


import fr.saejava.modele.*;

import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnexionMySQL() throws ClassNotFoundException{
			// Class.forName("org.mariadb.jdbc.Driver");

	}

	/**
	 * Connecte à la base de données MySQL avec les paramètres fournis.
	 *
	 * @param nomServeur le nom du serveur de base de données
	 * @param nomBase le nom de la base de données
	 * @param nomLogin le nom d'utilisateur pour la connexion
	 * @param motDePasse le mot de passe pour la connexion
	 * @throws SQLException si une erreur se produit lors de la connexion
	 */
	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		// si tout s'est bien passé la connexion n'est plus nulle
		mysql = DriverManager.getConnection("jdbc:mysql://servinfo-maria:3306/" + nomBase, nomLogin, motDePasse);
		// mysql = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + nomBase, nomLogin, motDePasse);

		this.connecte=this.mysql!=null;
	}

	/**
	 * Ferme la connexion à la base de données MySQL.
	 *
	 * @throws SQLException si une erreur se produit lors de la fermeture de la connexion
	 */
	public void close() throws SQLException {
		// fermer la connexion
		this.mysql.close();
		this.connecte=false;
	}

	/**
	 * Vérifie si la connexion à la base de données MySQL est établie.
	 * @return true si la connexion est établie, false sinon
	 */
    public boolean isConnecte() { return this.connecte;}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	/**
	 * Prépare une requête SQL pour exécution.
	 *
	 * @param requete la requête SQL à préparer
	 * @return un objet PreparedStatement pour exécuter la requête
	 * @throws SQLException si une erreur se produit lors de la préparation de la requête
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
