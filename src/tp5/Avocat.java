package tp5;

import org.bson.Document;

/**
 * Permet de représenter un tuple de la table avocat.
 */
public class Avocat
{
    private int id;
    private String prenom;
    private String nom;
    private int type;

    /**
     * Constructeur par défaut
     */
    public Avocat()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param a
     */
    public Avocat(Document a)
    {
        this(a.getInteger("id"), a.getString("prenom"), a.getString("nom"), a.getInteger("type"));
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param prenom
     * @param nom
     * @param type
     */
    public Avocat(int id, String prenom, String nom, int type)
    {
        super();
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     */
    public Avocat(int id)
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
     * Retourne l'objet courant sous forme de document
     * 
     * @return Document
     */
    public Document toDocument()
    {
        return new Document().append("id", id).append("prenom", prenom).append("nom", nom).append("type", type);
    }
}