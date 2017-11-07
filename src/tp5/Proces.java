/**
 * Permet de représenter un tuple de la table proces.
 */
package tp5;

import java.sql.Date;

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
     * @param id
     */
    public Proces(int id)
    {
        this.id = id;
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param juge
     * @param date
     * @param devantJury
     * @param partieDefenderesse 
     * @param partiePoursuivant 
     */
    public Proces(int id, Juge juge, Date date, int devantJury, Partie partieDefenderesse,
            Partie partiePoursuivant)
    {
        this(id);
        this.juge_id = juge.getId();
        this.date = date;
        this.devantJury = devantJury;
        this.partieDefenderesse_id = partieDefenderesse.getId();
        this.partiePoursuivant_id = partiePoursuivant.getId();
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
}
