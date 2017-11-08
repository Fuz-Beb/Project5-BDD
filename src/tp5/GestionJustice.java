package tp5;

/**
 * Système de gestion d'un palais de justice
 */
public class GestionJustice
{
    private Connexion cx;

    private TableProces proces;
    private TablePartie partie;
    private TableSeance seance;
    private TableJury jury;
    private TableJuge juge;
    private TableAvocat avocat;

    private GestionProces gestionProces;
    private GestionPartie gestionPartie;
    private GestionSeance gestionSeance;
    private GestionJury gestionJury;
    private GestionJuge gestionJuge;
    private GestionAvocat gestionAvocat;

    /**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et les tables
     * 
     * @param serveur
     * @param bd
     * @param user
     * @param password
     * @throws IFT287Exception
     */
    public GestionJustice(String serveur, String bd, String user, String password) throws IFT287Exception
    {
        try
        {
            cx = new Connexion(serveur, bd, user, password);

            proces = new TableProces(cx);
            partie = new TablePartie(cx);
            seance = new TableSeance(cx);
            jury = new TableJury(cx);
            juge = new TableJuge(cx);
            avocat = new TableAvocat(cx);

            gestionProces = new GestionProces(proces, seance, juge, partie, jury);
            gestionPartie = new GestionPartie(partie, avocat);
            gestionSeance = new GestionSeance(seance, proces);
            gestionJury = new GestionJury(jury, proces);
            gestionJuge = new GestionJuge(juge, proces);
            gestionAvocat = new GestionAvocat(avocat);
        }
        catch (IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Fermer la connexion
     */
    public void fermer()
    {
        cx.fermer();
    }

    /**
     * @return the gestionProces
     */
    public GestionProces getGestionProces()
    {
        return gestionProces;
    }

    /**
     * @return the gestionPartie
     */
    public GestionPartie getGestionPartie()
    {
        return gestionPartie;
    }

    /**
     * @return the gestionSeance
     */
    public GestionSeance getGestionSeance()
    {
        return gestionSeance;
    }

    /**
     * @return the gestionJury
     */
    public GestionJury getGestionJury()
    {
        return gestionJury;
    }

    /**
     * @return the gestionJuge
     */
    public GestionJuge getGestionJuge()
    {
        return gestionJuge;
    }

    /**
     * @return the gestionAvocat
     */
    public GestionAvocat getGestionAvocat()
    {
        return gestionAvocat;
    }
}