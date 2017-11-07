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
     * @param id
     *            the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @param prenom
     *            the prenom to set
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @return avocat
     */
    public int getAvocat()
    {
        return avocat_id;
    }

    /**
     * @param avocat
     *            the avocat_id to set
     */
    public void setAvocat(int avocat)
    {
        this.avocat_id = avocat;
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
