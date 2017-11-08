package tp5;

import java.util.ArrayList;

/**
 * Gestion des transaction de la table juge.
 */
public class GestionJuge
{
    private TableJuge juge;
    private TableProces proces;

    /**
     * Constructeur de confort
     * 
     * @param juge
     * @param proces
     * @throws IFT287Exception
     */
    public GestionJuge(TableJuge juge, TableProces proces) throws IFT287Exception
    {
        juge.getConnexion();

        if (juge.getConnexion() != proces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de juge et de proces n'utilisent pas la même connexion au serveur");

        this.juge = juge;
        this.proces = proces;
    }

    /**
     * Ajout d'un nouveau juge dans la base de données
     * 
     * @param jugeArg
     * @throws IFT287Exception
     */
    public void ajouter(Juge jugeArg) throws IFT287Exception
    {
        try
        {
            if (juge.existe(jugeArg.getId()))
                throw new IFT287Exception("Le juge existe déjà : " + jugeArg.getId());

            juge.ajouter(jugeArg);
        }
        catch (IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Afficher la liste des juges actifs et disponibles
     * @return List<Juge>
     */
    public ArrayList<Juge> affichage()
    {
        return juge.affichage();
    }

    /**
     * Retirer un juge
     * 
     * @param id
     * @throws IFT287Exception
     */
    public void retirer(int id) throws IFT287Exception
    {
        try
        {
            if (!juge.existe(id))
                throw new IFT287Exception("Juge inexistant : " + id);
            if (proces.jugeEnCours(id))
                throw new IFT287Exception("Le juge " + id + " n'a pas terminé tout ses procès");
            juge.retirer(id);
        }
        catch (IFT287Exception e)
        {
            throw e;
        }
    }

    /**
     * Retourne le juge demandé et reçu par TableJuge
     * 
     * @param id
     * @return Juge
     * @throws Exception
     */
    public Juge getJuge(int id) throws Exception
    {
        try
        {
            return juge.getJuge(id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}