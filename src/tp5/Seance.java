package tp5;

import java.sql.Date;

/**
 * Permet de représenter un tuple de la table seance.
 */

public class Seance
{
    private int id;
    private int proces_id;
    private Date date;

    /**
     * Constructeur par défaut
     */
    public Seance()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     */
    public Seance(int id)
    {
        this.id = id;
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param proces
     * @param date
     */
    public Seance(int id, Proces proces, Date date)
    {
        this(id);
        this.proces_id = proces.getId();
        this.date = date;
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
     * @return Proces
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
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }
}