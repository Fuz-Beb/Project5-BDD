package tp5;

/**
 * Gestion des transactions de la table avocat.
 */
public class GestionAvocat
{
    private TableAvocat avocat;

    /**
     * Constructeur de confort
     * 
     * @param avocat
     */
    public GestionAvocat(TableAvocat avocat)
    {
        this.avocat = avocat;
    }

    /**
     * Ajout d'un nouvelle avocat dans la base de données
     * 
     * @param avocatArg
     * @throws Exception
     */
    public void ajouter(Avocat avocatArg) throws Exception
    {
        try
        {
            if (avocat.existe(avocatArg.getId()))
                throw new IFT287Exception("L'avocat existe déjà : " + avocatArg.getId());

            avocat.ajouter(avocatArg);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    /**
     * Retourne l'avocat demandé et reçu par TableAvocat
     * 
     * @param id
     * @return Avocat
     */
    public Avocat getAvocat(int id)
    {
        Avocat avocatObj = null;
        avocatObj = avocat.getAvocat(id);

        return avocatObj;
    }
}