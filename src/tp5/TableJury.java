package tp5;

import static com.mongodb.client.model.Filters.eq;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Updates.*;

/**
 * Permet d'effectuer les accès à la table jury.
 */
public class TableJury
{
    private Connexion cx;
    private MongoCollection<Document> juryCollection;

    /**
     * Création d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     * 
     * @param cx
     */
    public TableJury(Connexion cx)
    {
        this.cx = cx;
        juryCollection = cx.getDatabase().getCollection("Jury");
    }

    /**
     * Retourne la commande associée
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Objet jury associé à un jury de la base de données
     * 
     * @param id
     * @return Jury
     */
    public Jury getJury(int id)
    {
        Document j = juryCollection.find(eq("nas", id)).first();
        if (j != null)
        {
            return new Jury(j);
        }

        return null;
    }

    /**
     * Vérifie si le jury existe
     * 
     * @param jury
     * @return boolean
     */
    public boolean existe(Jury jury)
    {
        return juryCollection.find(eq("nas", jury.getNas())).first() != null;
    }

    /**
     * Affiche la liste des jurys
     * 
     * @return String
     */
    public List<Jury> affichage()
    {
        return null;
//        return stmtSelect.getResultList();
    }

    /**
     * Ajout d'un nouveau jury dans la base de données
     * 
     * @param jury
     */
    public void ajouter(Jury jury)
    {
        juryCollection.insertOne(jury.toDocument());
    }

    /**
     * Assigner un proces à un jury
     * 
     * @param proces_id
     * @param idJury
     */
    public void assignerProces(int idJury, int proces_id)
    {
        juryCollection.updateOne(eq("nasJury", idJury), set("proces_id", proces_id));
    }
}