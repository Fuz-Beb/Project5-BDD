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
     * @param nas
     *            the nas to set
     */
    public void setNas(int nas)
    {
        this.nas = nas;
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
     * @return the sexe
     */
    public String getSexe()
    {
        return sexe;
    }

    /**
     * @param sexe
     *            the sexe to set
     */
    public void setSexe(String sexe)
    {
        this.sexe = sexe;
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
     * @return the proces
     */
    public int getProces()
    {
        return proces_id;
    }

    /**
     * @param proces
     */
    public void setProces(int proces)
    {
        this.proces_id = proces;
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