package tp5;

import org.bson.Document;

/**
 * Permet de représenter un tuple de la table juge.
 */

public class Juge
{
    private int id;
    private String prenom;
    private String nom;
    private int age;
    private boolean disponible;
    private boolean quitterJustice;

    /**
     * Constructeur par défaut
     */
    public Juge()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param a
     */
    public Juge(Document a)
    {
        this(a.getInteger("id"), a.getString("prenom"), a.getString("nom"), a.getInteger("age"));
        this.disponible = a.getBoolean("disponible");
        this.quitterJustice = a.getBoolean("quitterJustice");
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param prenom
     * @param nom
     * @param age
     */
    public Juge(int id, String prenom, String nom, int age)
    {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.disponible = true;
        this.quitterJustice = false;
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
     * @return un document juge
     */
    public Document toDocument()
    {
        return new Document().append("id", id).append("prenom", prenom).append("nom", nom).append("age", age)
                .append("disponible", disponible).append("quitterJustice", quitterJustice);
    }

    @Override
    public String toString()
    {
        return "Juge [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + "]";
    }
}