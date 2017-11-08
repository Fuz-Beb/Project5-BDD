package tp5;

import org.bson.Document;

/**
 * Permet de représenter un tuple de la table partie.
 *
 */

public class Partie
{
    private int id;
    private String prenom;
    private String nom;
    private int avocat_id;

    /**
     * Constructeur par défaut
     */
    public Partie()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param p
     */
    public Partie(Document p)
    {
        this(p.getInteger("id"), p.getString("prenom"), p.getString("nom"), p.getInteger("avocat_id"));
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param prenom
     * @param nom
     * @param avocat_id
     */
    public Partie(int id, String prenom, String nom, int avocat_id)
    {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.avocat_id = avocat_id;
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     */
    public Partie(int id)
    {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return avocat
     */
    public int getAvocat()
    {
        return avocat_id;
    }

    /**
     * Retourne l'objet courant sous forme de document
     * 
     * @return Document
     */
    public Document toDocument()
    {
        return new Document().append("id", id).append("prenom", prenom).append("nom", nom).append("avocat_id",
                avocat_id);
    }
}
