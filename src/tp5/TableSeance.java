package tp5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Filters.*;

import static com.mongodb.client.model.Filters.eq;

/**
 * Permet d'effectuer les accès à la table seance.
 *
 */
public class TableSeance
{
    private Connexion cx;
    private MongoCollection<Document> seanceCollection;

    /**
     * Constructeur de confort. Creation d'une instance. Précompilation
     * d'énoncés SQL.
     * 
     * @param cx
     */
    public TableSeance(Connexion cx)
    {
        this.cx = cx;
        seanceCollection = cx.getDatabase().getCollection("Partie");
    }

    /**
     * Affichage des seances lie a un proces
     * 
     * @param id
     * @return ArrayList<Seance>
     */
    public ArrayList<Seance> affichage(int id)
    {
        MongoCursor<Document> seance = seanceCollection.find().iterator();
        ArrayList<Seance> seanceListe = new ArrayList<Seance>();

        while (seance.hasNext())
        {
            Seance s = new Seance(seance.next());
            seanceListe.add(s);
        }

        return seanceListe;
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
     * Objet seance associé à une seance de la base de données
     * 
     * @param id
     * @return Seance
     */
    public Seance getSeance(int id)
    {
        Document s = seanceCollection.find(eq("id", id)).first();
        if (s != null)
        {
            return new Seance(s);
        }

        return null;
    }

    /**
     * Suppresion des seances prevues du proces
     * 
     * @param id
     */
    public void supprimerSeancesProcesTermine(int id)
    {
        seanceCollection.deleteMany(
                combine(eq("id", id), gt("date", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))));
    }

    /**
     * Methode de traitement pour effectuerSupprimerSeance
     * 
     * @param seance_id
     */
    public void supprimer(int seance_id)
    {
        seanceCollection.deleteOne(eq("id", seance_id));
    }

    /**
     * Verification de l'existance d'un proces
     * 
     * @param id
     * @return boolean
     */
    public boolean existe(int id)
    {
        return seanceCollection.find(eq("id", id)).first() != null;
    }

    /**
     * Vérification que la seance n'est pas encore passee
     * 
     * @param id
     * @return boolean
     */
    public boolean seancePassee(int id)
    {
        return seanceCollection
                .find(combine(eq("id", id), lt("date", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))))
                .first() != null;
    }

    /**
     * Ajout de la seance
     * 
     * @param seance
     */
    public void ajout(Seance seance)
    {
        seanceCollection.insertOne(seance.toDocument());
    }
}