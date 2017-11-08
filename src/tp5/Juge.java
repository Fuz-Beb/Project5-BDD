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
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible()
    {
        return disponible;
    }

    /**
     * @param disponible
     *            the disponible to set
     */
    public void setDisponible(boolean disponible)
    {
        this.disponible = disponible;
    }

    /**
     * @return the quitterJustice
     */
    public boolean isQuitterJustice()
    {
        return quitterJustice;
    }

    /**
     * @param quitterJustice
     *            the quitterJustice to set
     */
    public void setQuitterJustice(boolean quitterJustice)
    {
        this.quitterJustice = quitterJustice;
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
}