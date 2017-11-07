package tp5;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

/**
 * Permet d'effectuer les accès à la table partie.
 */
public class TablePartie
{
    private MongoCollection<Document> partieCollection;
    private Connexion cx;

    /**
     * Constructeur de confort. Creation d'une instance. Précompilation
     * d'énoncés SQL.
     * 
     * @param cx
     */
    public TablePartie(Connexion cx)
    {
        this.cx = cx;
        partieCollection = cx.getDatabase().getCollection("Partie");
    }

    /**
     * Retourner la connexion associée.
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Retourne le partie demandé
     * 
     * @param id
     * @return Partie
     */
    public Partie getPartie(int id)
    {
        Document p = partieCollection.find(eq("id", id)).first();
        if (p != null)
        {
            return new Partie(p);
        }

        return null;
    }

    /**
     * Vérifie si un partie existe.
     * 
     * @param partie_id
     * @return boolean
     */
    public boolean existe(int partie_id)
    {
        return partieCollection.find(eq("id", partie_id)).first() != null;
    }

    /**
     * Ajout d'un nouveau partie
     * 
     * @param partie
     */
    public void ajout(Partie partie)
    {
        partieCollection.insertOne(partie.toDocument());
    }
}