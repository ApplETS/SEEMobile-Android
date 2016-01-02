package ca.etsmtl.applets.seemobile.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import ca.etsmtl.applets.seemobile.model.Erreur;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.PosteResult;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class PosteDeserializer implements JsonDeserializer<PosteResult> {
    @Override
    public PosteResult deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonElement base = je.getAsJsonObject().get("obtenirPosteResult");

        JsonElement donnees = base.getAsJsonObject().get("donnees");
        JsonElement error = base.getAsJsonObject().get("erreur");

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm")
                .create();

        PosteFromApi posteFromApi = gson.fromJson(donnees, PosteFromApi.class);

        Erreur erreur = new Gson().fromJson(error, Erreur.class);
        Poste poste = posteFromApi.getPoste();

        PosteResult posteResult = new PosteResult(erreur, poste);


        return posteResult;

    }


    class PosteFromApi {
        String guid;
        int duree;
        boolean estFavori;
        String lieu;
        String numeroPoste;
        String nomEmployeur;
        String nomPoste;
        boolean aPostule;
        PosteDetailsFromApi details;

        public PosteFromApi() {
        }

        public Poste getPoste() {
            Poste poste = new Poste();
            poste.setGuid(guid);
            poste.setDuree(duree);
            poste.setEstFavori(estFavori);
            poste.setLieu(lieu);
            poste.setNumeroPoste(numeroPoste);
            poste.setNomEmployeur(nomEmployeur);
            poste.setNomPoste(nomPoste);
            poste.setaPostule(aPostule);

            poste.setCodePostal(details.codePostal);
            poste.setCoordonnateur(details.coordonnateur);
            poste.setDatePostulationLimite(details.datePostulationLimite);
            poste.setDescription(details.description);
            poste.setLangueCv(details.langueCv);
            poste.setMissionEmployeur(details.missionEmployeur);
            poste.setSiteWebEmployeur(details.siteWebEmployeur);
            poste.setTypeEmployeur(details.typeEmployeur);

            return poste;
        }
    }

    class PosteDetailsFromApi {
        String codePostal;
        String coordonnateur;
        Date datePostulationLimite;
        String description;
        String langueCv;
        String missionEmployeur;
        String siteWebEmployeur;
        String typeEmployeur;

        public PosteDetailsFromApi() {
        }
    }


}