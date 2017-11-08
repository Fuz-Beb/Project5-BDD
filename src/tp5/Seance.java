package tp5;

import java.util.Date;

import org.bson.Document;

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
     * @param s
     */
    public Seance(Document s)
    {
        this(s.getInteger("id"), (int) s.getInteger("proces_id"), s.getDate("date"));
    }

    /**
     * Constructeur de confort
     * 
     * @param id
     * @param proces_id
     * @param date
     */
    public Seance(int id, int proces_id, Date date)
    {
        this(id);
        this.proces_id = proces_id;
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

    /**
     * Retourne l'objet courant sous forme de document
     * 
     * @return Document
     */
    public Document toDocument()
    {
        return new Document().append("id", id).append("proces_id", proces_id).append("date", date);
    }

    @Override
    public String toString()
    {
        return "Seance [id=" + id + ", proces_id=" + proces_id + ", date=" + date + "]";
    }
}