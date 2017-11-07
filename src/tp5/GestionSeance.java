package tp5;

import java.util.List;

/**
 * Gestion des transactions de la table seance.
 */
public class GestionSeance
{
    private TableSeance seance;
    private TableProces proces;

    /**
     * Constructeur de confort
     * 
     * @param seance
     * @param proces
     * @throws IFT287Exception
     */
    public GestionSeance(TableSeance seance, TableProces proces) throws IFT287Exception
    {
        seance.getConnexion();
        if (seance.getConnexion() != proces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de TableSeance et de TableProces n'utilisent pas la même connexion au serveur");
        this.seance = seance;
        this.proces = proces;
    }

    /**
     * Ajout d'une nouvelle seance dans la base de données. S'il existe déjà,
     * une exception est levée.
     * 
     * @param seanceArg
     * @throws IFT287Exception
     */
    public void ajout(Seance seanceArg) throws IFT287Exception
    {
        // Vérification si la seance existe deja
        if (seance.existe(seanceArg.getId()))
            throw new IFT287Exception("La seance existe deja: " + seanceArg.getId());

        // Verification si le proces existe
        if (!proces.existe(seanceArg.getProces()))
            throw new IFT287Exception("Le proces " + seanceArg.getProces() + " n'existe pas.");

        // Verification si le proces specifie n'est pas termine
        if (!proces.verifierProcesTermine(seanceArg.getProces()))
            throw new IFT287Exception("Le proces " + seanceArg.getProces() + " est termine.");

        seance.ajout(seanceArg);
    }

    /**
     * Supprimer une seance
     * 
     * @param seanceArg
     * @throws Exception
     */
    public void supprimer(Seance seanceArg) throws Exception
    {
        // Vérification si la seance existe
        if (!seance.existe(seanceArg.getId()))
            throw new IFT287Exception("La seance n'existe pas : " + seanceArg);

        // Vérification que la seance n'est pas encore passée
        if (seance.seancePassee(seanceArg.getId()))
            throw new IFT287Exception("La seance " + seanceArg + " est déjà passée.");

        seance.supprimer(seanceArg.getId());
    }

    /**
     * Retourne la liste des seances liées à un proces pour affichage
     * 
     * @param id
     * @return List<Seance>
     * @throws IFT287Exception
     */
    public List<Seance> affichage(int id) throws IFT287Exception
    {
        List<Seance> list = null;
        if (!proces.existe(id))
            throw new IFT287Exception("Le proces " + id + "n'existe pas");
        else
            list = seance.affichage(id);
        return list;
    }

    /**
     * Retourne le partie demandé et reçu par TableSeance
     * 
     * @param id
     * @return Seance
     */
    public Seance getSeance(int id)
    {
        Seance list = null;
        list = seance.getSeance(id);

        return list;
    }
}