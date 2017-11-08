package tp5;

import java.util.ArrayList;

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
        try
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
        catch (IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Supprimer une seance
     * 
     * @param id
     * @throws Exception
     */
    public void supprimer(int id) throws Exception
    {
        try
        {
            // Vérification si la seance existe
            if (!seance.existe(id))
                throw new IFT287Exception("La seance n'existe pas : " + id);

            // Vérification que la seance n'est pas encore passée
            if (seance.seancePassee(id))
                throw new IFT287Exception("La seance " + id + " est déjà passée.");

            seance.supprimer(id);
        }
        catch (IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Retourne la liste des seances liées à un proces pour affichage
     * 
     * @param id
     * @return ArrayList<Seance>
     */
    public ArrayList<Seance> affichage(int id)
    {
        return seance.affichage(id);
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