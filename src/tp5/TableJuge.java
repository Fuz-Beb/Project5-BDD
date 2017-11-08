package tp5;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;

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
     * @return ArrayList<Juge>
     */
    public ArrayList<Juge> affichage()
    {
        MongoCursor<Document> juge = jugeCollection.find(combine(eq("quitterJustice", false), eq("disponible", true))).iterator();
        ArrayList<Juge> listeJuges = new ArrayList<Juge>();

        while (juge.hasNext())
        {
            Juge j = new Juge(juge.next());
            listeJuges.add(j);
        }

        return listeJuges;
    }

    /**
     * Ajout d'un nouveau juge dans la base de données
     * 
     * @param juge
     */
    public void ajouter(Juge juge)
    {
        jugeCollection.insertOne(juge.toDocument());
    }

    /**
     * Retirer le juge de la base de données
     * 
     * @param id
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
     */
    public void changerDisponibilite(boolean disponible, int id)
    {
        jugeCollection.updateOne(eq("id", id), set("disponible", disponible));
    }
}