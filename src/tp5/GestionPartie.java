package tp5;

/**
 * Gestion des transactions de la table partie.
 */
public class GestionPartie
{
    private TablePartie partie;
    private TableAvocat avocat;
    /**
     * Constructeur de confort
     * 
     * @param partie
     * @param avocat
     * @throws IFT287Exception
     */
    public GestionPartie(TablePartie partie, TableAvocat avocat) throws IFT287Exception
    {
        partie.getConnexion();
        if (partie.getConnexion() != avocat.getConnexion())
            throw new IFT287Exception(
                    "Les instances de TablePartie et de TableAvocat n'utilisent pas la même connexion au serveur");
        this.partie = partie;
        this.avocat = avocat;
    }

    /**
     * Ajout d'un nouveau partie dans la base de données. S'il existe déjà, une
     * exception est levée.
     * 
     * @param partieArg
     * @throws IFT287Exception 
     */
    public void ajout(Partie partieArg) throws IFT287Exception
    {
        // Vérifie si le partie existe déjà
        if (partie.existe(partieArg.getId()))
            throw new IFT287Exception("Partie existe déjà: " + partieArg.getId());

        // Vérifie si l'avocat existe
        if (!avocat.existe(partieArg.getAvocat()))
            throw new IFT287Exception("L'avocat " + partieArg.getAvocat() + "n'existe pas.");

        // Ajout du partie
        partie.ajout(partieArg);
    }

    /**
     * Retourne le partie demandé et reçu par TablePartie
     * 
     * @param id
     * @return Partie
     */
    public Partie getPartie(int id)
    {
        Partie list = null;
        list = partie.getPartie(id);
        return list;
    }
}