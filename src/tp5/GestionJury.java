package tp5;

import java.util.ArrayList;

/**
 * Gestion des transactions de la table jury.
 */
public class GestionJury
{
    private TableJury jury;
    private TableProces proces;

    /**
     * Constructeur de confort
     * 
     * @param jury
     * @param proces
     * @throws IFT287Exception
     */
    public GestionJury(TableJury jury, TableProces proces) throws IFT287Exception
    {
        jury.getConnexion();

        if (jury.getConnexion() != proces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de juge et de proces n'utilisent pas la même connexion au serveur");

        this.jury = jury;
        this.proces = proces;
    }

    /**
     * Ajout d'une jury dans la base de données
     * 
     * @param juryArg
     * @throws Exception
     */
    public void ajouter(Jury juryArg) throws Exception
    {
        try
        {
            if (jury.existe(juryArg))
                throw new IFT287Exception("Jury existe déjà : " + juryArg.getNas());
            jury.ajouter(juryArg);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * Afficher la liste des jurys
     * 
     * @return List<Jury>
     */
    public ArrayList<Jury> affichage()
    {
        return jury.affichage();
    }

    /**
     * Assigner un proces à un jury
     * 
     * @param idJury
     * @param proces_id
     * @throws IFT287Exception
     */
    public void assignerProces(int idJury, int proces_id) throws IFT287Exception
    {
        if (!proces.existe(proces_id))
            throw new IFT287Exception("Proces n'existe pas : " + proces_id);
        if (!proces.devantJury(proces_id))
            throw new IFT287Exception("Le proces " + proces_id + "doit se tenir devant un juge seul");

        jury.assignerProces(idJury, proces_id);
    }

    /**
     * Retourne le jury demandé et reçu par TableJury
     * 
     * @param id
     * @return Jury
     */
    public Jury getJury(int id)
    {
        Jury list = null;

        list = jury.getJury(id);

        return list;

    }
}