package tp5;

import java.util.Date;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;

/**
 * Permet d'effectuer les accès à la table proces.
 */
public class TableProces
{
    private MongoCollection<Document> procesCollection;
    private Connexion cx;

    /**
     * Constructeur de confort. Creation d'une instance. Précompilation
     * d'énoncés SQL.
     * 
     * @param cx
     */
    public TableProces(Connexion cx)
    {
        this.cx = cx;
        procesCollection = cx.getDatabase().getCollection("Proces");
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
     * Objet proces associé à un proces de la base de données
     * 
     * @param id
     * @return Proces
     * @throws Exception
     */
    public Proces getProces(int id) throws Exception
    {
        Document a = procesCollection.find(eq("id", id)).first();
        if (a != null)
        {
            return new Proces(a);
        }
        return null;
    }

    /**
     * Verification de l'existance d'un proces
     * 
     * @param idProces
     * @return boolean
     */
    public boolean existe(int idProces)
    {
        return procesCollection.find(eq("id", idProces)).first() != null;
    }

    /**
     * Affichage des elements de proces
     * 
     * @param id
     * @return Proces
     */
    public Proces affichage(int id)
    {
        MongoCursor<Document> proces = procesCollection.find().iterator();

        Proces p = new Proces(proces.next());
        return p;
    }

    /**
     * Vérification que le proces a atteint sa date initiale
     * 
     * @param idProces
     * @return boolean
     */
    public boolean compareDate(int idProces)
    {
        // A REVOIR
        return procesCollection.find(combine(eq("id", idProces), lt("date", new Date()))).first() != null;
    }

    /**
     * Terminer le proces
     * 
     * @param decision
     * @param idProces
     */
    public void terminer(String decision, int idProces)
    {
        procesCollection.updateOne(eq("id", idProces), set("decision", decision));
    }

    /**
     * Permet de récuperer le juge du proces
     * 
     * @param idProces
     * @return int
     * 
     */
    public int getJugeProces(int idProces)
    {
        return procesCollection.find(eq("id", idProces)).first().getInteger("juge_id");
    }

    /**
     * Verifier si un juge a des proces en cours
     * 
     * @param idJuge
     * @return boolean
     */
    public boolean jugeEnCours(int idJuge)
    {
        return procesCollection.find(combine(eq("juge_id", idJuge), eq("decision", null))).first() != null;
    }

    /**
     * Ajout du proces
     * 
     * @param proces
     */
    public void creer(Proces proces)
    {
        procesCollection.insertOne(proces.toDocument());
    }

    /**
     * Verification si le proces specifie n'est pas termine
     * 
     * @param idProces
     * @return boolean
     */
    public boolean verifierProcesTermine(int idProces)
    {
        return procesCollection.find(combine(eq("id", idProces), eq("decision", null))).first() != null;
    }

    /**
     * Permet de savoir si un proces est devant un jury ou juge seul ou les deux
     * 
     * @param idProces
     * @return boolean
     */
    public boolean devantJury(int idProces)
    {
        return procesCollection.find(combine(eq("id", idProces), eq("devantJury", 1))).first() != null;
    }
}