package tp5;

import org.bson.Document;

/**
 * Permet de représenter un tuple de la table jury.
 */

public class Jury
{
    private int nas;
    private String prenom;
    private String nom;
    private String sexe;
    private int age;
    private int proces_id = -1;

    /**
     * Constructeur par défaut
     */
    public Jury()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param nas
     */
    public Jury(int nas)
    {
        this.nas = nas;
    }

    /**
     * Constructeur de confort
     * 
     * @param j
     */
    public Jury(Document j)
    {
        this(j.getInteger("nas"), j.getString("prenom"), j.getString("nom"), j.getString("sexe"), j.getInteger("age"));
    }

    /**
     * Constructeur de confort
     * 
     * @param nas
     * @param prenom
     * @param nom
     * @param sexe
     * @param age
     */
    public Jury(int nas, String prenom, String nom, String sexe, int age)
    {
        this(nas);
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    /**
     * @return the nas
     */
    public int getNas()
    {
        return nas;
    }

    /**
     * @return the proces_id
     */
    public int getProces_id()
    {
        return proces_id;
    }

    /**
     * Retourne l'objet courant sous forme de document
     * 
     * @return Document
     */
    public Document toDocument()
    {
        return new Document().append("nas", nas).append("prenom", prenom).append("nom", nom).append("sexe", sexe)
                .append("age", age).append("proces_id", proces_id);
    }

    @Override
    public String toString()
    {
        return "Jury [nas=" + nas + ", prenom=" + prenom + ", nom=" + nom + ", sexe=" + sexe + ", age=" + age
                + ", proces_id=" + proces_id + "]";
    }
}