package tp5;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

/**
 * Permet d'effectuer les accès à la table avocat.
 */
public class TableAvocat
{
    private MongoCollection<Document> avocatCollection;
    private Connexion cx;

    /**
     * Création d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     * 
     * @param cx
     */
    public TableAvocat(Connexion cx)
    {
        this.cx = cx;
        avocatCollection = cx.getDatabase().getCollection("Avocat");
    }

    /**
     * Retourner la connexion associée
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Retourne l'avocat demandé
     * 
     * @param id
     * @return Avocat
     */
    public Avocat getAvocat(int id)
    {
        Document a = avocatCollection.find(eq("id", id)).first();
        if (a != null)
        {
            return new Avocat(a);
        }

        return null;
    }

    /**
     * Vérifie si l'avocat existe
     * 
     * @param avocat_id
     * @return boolean
     */
<<<<<<< HEAD
    public boolean existe(int avocat_id)
    {
        return avocatCollection.find(eq("id", avocat_id)).first() != null;
=======
    public boolean existe(int id)
    {
        return avocatCollection.find(eq("id", id)).first() != null;
>>>>>>> 14e670544909b888b2acf62cf901dd7383c2a8f6
    }

    /**
     * Ajout d'un nouvelle avocat dans la base de données
     * 
     * @param avocat
     */
    public void ajouter(Avocat avocat)
    {
        avocatCollection.insertOne(avocat.toDocument());
    }
}