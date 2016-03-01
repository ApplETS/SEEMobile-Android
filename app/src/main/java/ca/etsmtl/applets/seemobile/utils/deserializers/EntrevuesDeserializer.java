package ca.etsmtl.applets.seemobile.utils.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Entrevue;
import ca.etsmtl.applets.seemobile.model.Erreur;
import ca.etsmtl.applets.seemobile.model.ListeEntrevuesResult;
import ca.etsmtl.applets.seemobile.model.ListePostesResult;
import ca.etsmtl.applets.seemobile.model.Poste;

/**
 * Created by gnut3ll4 on 29/02/16.
 */
public class EntrevuesDeserializer implements JsonDeserializer<ListeEntrevuesResult> {
    @Override
    public ListeEntrevuesResult deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonElement base = je.getAsJsonObject().get("obtenirEntrevuesAConfirmerResult");
        if(base == null) {
            base = je.getAsJsonObject().get("obtenirEntrevuesAVenirResult");
        }
        JsonElement donnees = base.getAsJsonObject().get("donnees");
        JsonElement error = base.getAsJsonObject().get("erreur");

        Erreur erreur = new Gson().fromJson(error, Erreur.class);

        List<Entrevue> entrevues = new Gson().fromJson(donnees, new TypeToken<List<Entrevue>>() {
        }.getType());

        ListeEntrevuesResult listeEntrevuesResult = new ListeEntrevuesResult(erreur, entrevues);

        return listeEntrevuesResult;

    }
}