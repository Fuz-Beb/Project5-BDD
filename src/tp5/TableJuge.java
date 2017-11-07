package tp5;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;

/**
 * Permet d'effectuer les accès à la collection juge
 */
public class TableJuge
{
    private MongoCollection<Document> jugeCollection;
    private Connexion cx;

    /**
     * Création d'une instance de Juge
     * 
     * @param cx
     */
    public TableJuge(Connexion cx)
    {
        this.cx = cx;
        jugeCollection = cx.getDatabase().getCollection("Juge");
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
     * Objet juge associé à un juge de la base de données
     * 
     * @param id
     * @return Juge
     * @throws Exception
     */
    public Juge getJuge(int id) throws Exception
    {
        Document a = jugeCollection.find(eq("id", id)).first();
        if (a != null)
        {
            return new Juge(a);
        }
        return null;
    }

    /**
     * Vérifie si le juge existe
     * 
     * @param id
     * @return boolean
     */
    public boolean existe(int id)
    {
        return jugeCollection.find(eq("id", id)).first() != null;
    }

    /**
     * Afficher la liste des juges actifs et disponibles
     * 
     * @return List<Juge>
     */
    public List<Juge> affichage()
    {
        return stmtSelect.getResultList();
    }

    /**
     * Ajout d'un nouveau juge dans la base de données
     * 
     * @param juge
     * @return Le juge qui a été ajouté
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public void ajouter(Juge juge)
    {
        jugeCollection.insertOne(juge.toDocument());
    }

    /**
     * Retirer le juge de la base de données
     * 
     * @param id
     * @return vrai si suppresion OK sinon faux
     */
    public void retirer(int id)
    {
        jugeCollection.updateOne(eq("id", id), combine(set("disponible", false), set("quitterJustice", true)));
    }

    /**
     * Changer la disponibilite d'un juge
     * 
     * @param disponible
     * @param id
     * @return boolean
     */
    public void changerDisponibilite(boolean disponible, int id)
    {
        jugeCollection.updateOne(eq("id", id), set("disponible", disponible));
    }
}