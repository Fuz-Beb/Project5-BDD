package tp5;

/**
 * Gestion des transactions de la table proces
 */
public class GestionProces
{
    private TableProces proces;
    private TableSeance seance;
    private TableJuge juge;
    private TablePartie partie;

    /**
     * Constructeur de confort
     * 
     * @param proces
     * @param seance
     * @param juge
     * @param partie
     * @throws IFT287Exception
     */
    public GestionProces(TableProces proces, TableSeance seance, TableJuge juge, TablePartie partie)
            throws IFT287Exception
    {
        proces.getConnexion();
        if (proces.getConnexion() != seance.getConnexion())
            throw new IFT287Exception(
                    "Les instances de TableProces et TableSeance n'utilisent pas la même connexion au serveur");
        if (proces.getConnexion() != juge.getConnexion())
            throw new IFT287Exception(
                    "Les instances de TableProces et TableJuge n'utilisent pas la même connexion au serveur");
        if (proces.getConnexion() != partie.getConnexion())
            throw new IFT287Exception(
                    "Les instances de TableProces et TablePartie n'utilisent pas la même connexion au serveur");

        this.proces = proces;
        this.seance = seance;
        this.juge = juge;
        this.partie = partie;
    }

    /**
     * Methode d'affichage d'un proces
     * 
     * @param id
     * @return Proces
     */
    public Proces affichage(int id)
    {
        return proces.affichage(id);
    }

    /**
     * Methode de traitement pour effectuerTerminerProces
     * 
     * @param id
     * @param decisionProces
     * @throws Exception
     */
    public void terminer(int id, String decisionProces) throws Exception
    {
        try
        {
            int idJuge = 0;

            // Verification de la valeur de la decision
            if (!decisionProces.equals("0") && !decisionProces.equals("1"))
                throw new IFT287Exception(
                        "Impossible de terminer le proces " + id + " car la valeur doit être 0 ou 1.");

            // Vérification que le proces existe
            if (!proces.existe(id))
                throw new IFT287Exception("Le proces " + id + " n'existe pas.");

            // Vérification que le proces a atteint sa date initiale
            if (!proces.compareDate(id))
                throw new IFT287Exception("Le proces " + id + " n'a pas atteint sa date initiale.");

            proces.terminer(decisionProces, id);

            idJuge = proces.getJugeProces(id);

            if (!proces.jugeEnCours(idJuge))
                juge.changerDisponibilite(true, idJuge);

            seance.supprimerSeancesProcesTermine(id);
        }
        catch(IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Permet de creer un proces
     * 
     * @param procesArg
     * @throws Exception
     */
    public void creer(Proces procesArg) throws Exception
    {
        try
        {
            if (procesArg.getDevantJury() != 0 && procesArg.getDevantJury() != 1)
                throw new IFT287Exception("Impossible de creer le proces " + procesArg.getId()
                        + "car le champ devantJury ne peut être que 0 ou 1");

            // Vérification que le proces n'existe pas déjà
            if (proces.existe(procesArg.getId()))
                throw new IFT287Exception("Le proces " + procesArg.getId() + " existe déjà.");
            // Vérification que l'id du juge est correcte
            if (!juge.existe(procesArg.getId()))
                throw new IFT287Exception("Le juge " + procesArg.getId() + " n'existe pas.");
            if (!partie.existe(procesArg.getPartieDefenderesse()))
                throw new IFT287Exception(
                        "La partie defenderesse " + procesArg.getPartieDefenderesse() + " n'existe pas.");
            if (!partie.existe(procesArg.getPartiePoursuivant()))
                throw new IFT287Exception(
                        "La partie poursuivante " + procesArg.getPartiePoursuivant() + " n'existe pas.");

            proces.creer(procesArg);

            // Rendre le juge non disponible
            juge.changerDisponibilite(false, procesArg.getId());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * Retourne le proces demandé et reçu par TableProces
     * 
     * @param id
     * @return Partie
     * @throws Exception
     */
    public Proces getProces(int id) throws Exception
    {
        try
        {
            return proces.getProces(id);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}