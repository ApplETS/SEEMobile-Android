package ca.etsmtl.applets.seemobile.service;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
class PostulationsDeserializer implements JsonDeserializer<List<Postulation>> {
    @Override
    public List<Postulation> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        // Get the "content" element from the parsed JSON


        JsonElement base = je.getAsJsonObject().get("obtenirPostulationsResult");
        JsonElement content = base.getAsJsonObject().get("donnees");

//        JsonElement content = je.getAsJsonObject().get("donnees");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(content, type);

    }
}