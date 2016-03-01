package ca.etsmtl.applets.seemobile.utils.deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Entrevue;
import ca.etsmtl.applets.seemobile.model.Erreur;
import ca.etsmtl.applets.seemobile.model.ListeEntrevuesResult;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class EntrevuesDeserializer implements JsonDeserializer<ListeEntrevuesResult> {
    @Override
    public ListeEntrevuesResult deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonElement base = je.getAsJsonObject().get("obtenirEntrevuesAConfirmerResult");
        if (base == null) {
            base = je.getAsJsonObject().get("obtenirEntrevuesAVenirResult");
        }
        JsonElement donnees = base.getAsJsonObject().get("donnees");
        JsonElement error = base.getAsJsonObject().get("erreur");

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm")
                .create();

        List<EntrevueFromApi> entrevueFromApis = gson.fromJson(donnees, new TypeToken<List<EntrevueFromApi>>() {
        }.getType());

        Erreur erreur = new Gson().fromJson(error, Erreur.class);

        List<Entrevue> entrevues = new ArrayList<>();
        for (EntrevueFromApi entrevueFromApi : entrevueFromApis) {
            entrevues.add(entrevueFromApi.getEntrevue());
        }


        ListeEntrevuesResult listeEntrevuesResult = new ListeEntrevuesResult(erreur, entrevues);


        return listeEntrevuesResult;

    }


    class EntrevueFromApi {

        String guid;
        String type;
        EntrevueDetailsFromApi details;
        String numeroPoste;
        boolean posteAnnule;
        String nomEmployeur;


        public EntrevueFromApi() {
        }

        public Entrevue getEntrevue() {
            Entrevue entrevue = new Entrevue();
            entrevue.setGuid(guid);
            entrevue.setType(type);

            entrevue.setNumeroPoste(numeroPoste);
            entrevue.setPosteAnnule(posteAnnule);
            entrevue.setNomEmployeur(nomEmployeur);

            if (details != null) {
                entrevue.setDateEntrevue(details.dateEntrevue);
                entrevue.setLieu(details.lieu);
                entrevue.setSuperviseurs(details.superviseurs);
                entrevue.setInterviewers(details.interviewers);
                entrevue.setRemarque(details.remarque);
            }
            return entrevue;
        }
    }

    class EntrevueDetailsFromApi {
        Date dateEntrevue;
        String lieu;
        List<String> superviseurs;
        List<String> interviewers;
        String remarque;

        public EntrevueDetailsFromApi() {
        }
    }


}