package ca.etsmtl.applets.seemobile.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Erreur;
import ca.etsmtl.applets.seemobile.model.ListePostulations;
import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class PostulationsDeserializer implements JsonDeserializer<ListePostulations> {
    @Override
    public ListePostulations deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        // Get the "content" element from the parsed JSON


        JsonElement base = je.getAsJsonObject().get("obtenirPostulationsResult");
        JsonElement donnees = base.getAsJsonObject().get("donnees");
        JsonElement error = base.getAsJsonObject().get("erreur");

        Erreur erreur = new Gson().fromJson(error, Erreur.class);

        List<Postulation> postulations = new Gson().fromJson(donnees, new TypeToken<List<Postulation>>() {
        }.getType());


        ListePostulations listePostulations = new ListePostulations(erreur, postulations);

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return listePostulations;

    }
}