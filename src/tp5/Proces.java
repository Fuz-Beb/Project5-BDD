/**
 * Permet de représenter un tuple de la table proces.
 */
package tp5;

import java.util.Date;

import org.bson.Document;

/**
 * Permet de représenter un tuple de la table proces.
 *
 */

public class Proces
{
    private int id;
    private int juge_id;
    private Date date;
    private int devantJury;
    private int partieDefenderesse_id;
    private int partiePoursuivant_id;
    private String decision;

    /**
     * Constructeur par défaut
     */
    public Proces()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param a
     */
    public Proces(Document a)
    {
        this(a.getInteger("id"), a.getInteger("juge_id"), a.getDate("date"), a.getInteger("devantJury"),
                a.getInteger("partieDefenderesse_id"), a.getInteger("partiePoursuivant_id"), a.getString("decision"));
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param juge_id
     * @param date
     * @param devantJury
     * @param partieDefenderesse_id
     * @param partiePoursuivant_id
     * @param decision 
     */
    public Proces(int id, int juge_id, Date date, int devantJury, int partieDefenderesse_id, int partiePoursuivant_id, String decision)
    {
        this.id = id;
        this.juge_id = juge_id;
        this.date = date;
        this.devantJury = devantJury;
        this.partieDefenderesse_id = partieDefenderesse_id;
        this.partiePoursuivant_id = partiePoursuivant_id;
        this.decision = decision;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the juge
     */
    public int getJuge()
    {
        return juge_id;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @return the devantJury
     */
    public int getDevantJury()
    {
        return devantJury;
    }

    /**
     * @return the partieDefenderesse_id
     */
    public int getPartieDefenderesse()
    {
        return partieDefenderesse_id;
    }

    /**
     * @return the partiePoursuivant_id
     */
    public int getPartiePoursuivant()
    {
        return partiePoursuivant_id;
    }

    /**
     * @return the decision
     */
    public String getDecision()
    {
        return decision;
    }

    /**
     * Retourne l'objet courant sous forme de document
     * 
     * @return Document
     */
    public Document toDocument()
    {
        return new Document().append("id", id).append("juge_id", juge_id).append("date", date)
                .append("devantJury", devantJury).append("partieDefenderesse_id", partieDefenderesse_id)
                .append("partiePoursuivant_id", partiePoursuivant_id)
                .append("decision", decision);
    }

    @Override
    public String toString()
    {
        return "Proces [id=" + id + ", juge_id=" + juge_id + ", date=" + date + ", devantJury=" + devantJury
                + ", partieDefenderesse_id=" + partieDefenderesse_id + ", partiePoursuivant_id=" + partiePoursuivant_id
                + ", decision=" + decision + "]";
    }
}