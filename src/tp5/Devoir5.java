// Travail fait par :
// Pierrick BOBET - 17 131 792
// Rémy BOUTELOUP - 17 132 265

package tp5;

import java.io.*;
import java.util.StringTokenizer;

import java.sql.*;

/**
 * Interface du systeme de gestion d'une bibliothèque
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class Devoir5
{
    private static GestionJustice gestionJustice;

    /**
     * Ouverture de la BD, traitement des transacations et fermeture de la BD
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        Devoir5 instanceDevoir3 = null;

        if (args.length < 4)
        {
            System.out.println("Usage: java tp2.Devoir2 <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }

        try
        {
            instanceDevoir3 = new Devoir5(args[0], args[1], args[2], args[3]);

            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (instanceDevoir3 != null)
                instanceDevoir3.fermer();
        }
    }

    /**
     * Ouvre la connexion
     * 
     * @param serveur
     * @param bd
     * @param user
     * @param pass
     * @throws IFT287Exception
     * @throws SQLException
     */
    public Devoir5(String serveur, String bd, String user, String pass) throws IFT287Exception, SQLException
    {
        gestionJustice = new GestionJustice(serveur, bd, user, pass);
    }

    /**
     * Fermer la connexion
     * 
     * @throws SQLException
     */
    public void fermer() throws SQLException
    {
        gestionJustice.fermer();
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.println(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterJuge"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionJuge().ajouter(new Juge(readInt(tokenizer), readString(tokenizer),
                            readString(tokenizer), readInt(tokenizer)));
                }
                else if (command.equals("retirerJuge"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionJuge().retirer(readInt(tokenizer));
                }
                else if (command.equals("ajouterAvocat"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionAvocat().ajouter(new Avocat(readInt(tokenizer), readString(tokenizer),
                            readString(tokenizer), readInt(tokenizer)));
                }
                else if (command.equals("ajouterPartie"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionPartie().ajout(new Partie(readInt(tokenizer), readString(tokenizer),
                            readString(tokenizer), readInt(tokenizer)));
                }
                else if (command.equals("creerProces"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionProces().creer(new Proces(readInt(tokenizer), readInt(tokenizer),
                            readDate(tokenizer), readInt(tokenizer), readInt(tokenizer), readInt(tokenizer)));
                }
                else if (command.equals("inscrireJury"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionJury().ajouter(new Jury(readInt(tokenizer), readString(tokenizer),
                            readString(tokenizer), readString(tokenizer), readInt(tokenizer)));
                }
                else if (command.equals("assignerJury"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionJury().assignerProces(readInt(tokenizer), readInt(tokenizer));
                }
                else if (command.equals("ajouterSeance"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionSeance()
                            .ajout(new Seance(readInt(tokenizer), readInt(tokenizer), readDate(tokenizer)));
                }
                else if (command.equals("supprimerSeance"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionSeance().supprimer(readInt(tokenizer));
                }
                else if (command.equals("terminerProces"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    gestionJustice.getGestionProces().terminer(readInt(tokenizer), readString(tokenizer));
                }
                else if (command.equals("afficherJuges"))
                {
                    // Appel de la methode qui traite la transaction specifique
                    System.out.println("\nListe des juges actifs et disponibles :");

                    gestionJustice.getGestionJuge().affichage();
                }
                else if (command.equals("afficherProces"))
                {
                    int idProces = readInt(tokenizer);
                    // Appel de la methode qui traite la transaction specifique                   
                    System.out.println("Affichage du proces " + idProces);

                    gestionJustice.getGestionProces().affichage(idProces);
                    
                    System.out.println("\nListe des séances liées au proces " + idProces);

                    gestionJustice.getGestionSeance().affichage(idProces);                    
                }
                else if (command.equals("afficherJurys"))
                {
                    // Appel de la methode qui traite la transaction specifique                    
                    System.out.println("\nListe des jurys disponibles :");
                    gestionJustice.getGestionJury().affichage();
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(" " + e.toString());

            e.printStackTrace();
        }
    }
    // ****************************************************************
    // * Les methodes suivantes n'ont pas besoin d'etre modifiees *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     * 
     * @param args
     * @return BufferedReader
     * @throws FileNotFoundException
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}