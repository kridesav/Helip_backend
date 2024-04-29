package Backend.Helip.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import Backend.Helip.models.Feature;
import Backend.Helip.models.Geometry;
import Backend.Helip.models.Properties;

import java.io.IOException;

public class FeatureDeserializer extends JsonDeserializer<Feature> {
    @Override
    public Feature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // Manually construct the Feature object from the JsonNode
        Feature feature = new Feature();
        if (node.hasNonNull("type")) {
            feature.setType(node.get("type").asText());
        }

        // Deserialize properties
        if (node.hasNonNull("properties")) {
            JsonNode propertiesNode = node.get("properties");
            Properties properties = new Properties();
            if (propertiesNode.hasNonNull("id"))
                properties.setId(propertiesNode.get("id").asLong());
            if (propertiesNode.hasNonNull("katuosoite"))
                properties.setKatuosoite(propertiesNode.get("katuosoite").asText());
            if (propertiesNode.hasNonNull("kunta_nimi"))
                properties.setKuntaNimi(propertiesNode.get("kunta_nimi").asText());
            if (propertiesNode.hasNonNull("kuntanumer"))
                properties.setKuntanumer(propertiesNode.get("kuntanumer").asInt());
            if (propertiesNode.hasNonNull("lisatieto_"))
                properties.setLisatieto(propertiesNode.get("lisatieto_").asText());
            if (propertiesNode.hasNonNull("muokattu_v"))
                properties.setMuokattuV(propertiesNode.get("muokattu_v").asText());
            if (propertiesNode.hasNonNull("nimi_fi"))
                properties.setNimiFi(propertiesNode.get("nimi_fi").asText());
            if (propertiesNode.hasNonNull("nimi_se"))
                properties.setNimiSe(propertiesNode.get("nimi_se").asText());
            if (propertiesNode.hasNonNull("omistaja"))
                properties.setOmistaja(propertiesNode.get("omistaja").asText());
            if (propertiesNode.hasNonNull("piste_json"))
                properties.setPisteJson(propertiesNode.get("piste_json").asText());
            if (propertiesNode.hasNonNull("postinumer"))
                properties.setPostinumer(propertiesNode.get("postinumer").asText());
            if (propertiesNode.hasNonNull("postitoimi"))
                properties.setPostitoimi(propertiesNode.get("postitoimi").asText());
            if (propertiesNode.hasNonNull("puhelinnum"))
                properties.setPuhelinnum(propertiesNode.get("puhelinnum").asText());
            if (propertiesNode.hasNonNull("sahkoposti"))
                properties.setSahkoposti(propertiesNode.get("sahkoposti").asText());
            if (propertiesNode.hasNonNull("sijainti_i"))
                properties.setSijaintiI(propertiesNode.get("sijainti_i").asInt());
            if (propertiesNode.hasNonNull("tyyppi_n_1"))
                properties.setTyyppiN1(propertiesNode.get("tyyppi_n_1").asText());
            if (propertiesNode.hasNonNull("tyyppi_n_2"))
                properties.setTyyppiN2(propertiesNode.get("tyyppi_n_2").asText());
            if (propertiesNode.hasNonNull("tyyppi_nim"))
                properties.setTyyppiNim(propertiesNode.get("tyyppi_nim").asText());
            if (propertiesNode.hasNonNull("tyyppikood"))
                properties.setTyyppikood(propertiesNode.get("tyyppikood").asInt());
            if (propertiesNode.hasNonNull("www"))
                properties.setWww(propertiesNode.get("www").asText());
            if (propertiesNode.hasNonNull("x"))
                properties.setX(propertiesNode.get("x").asDouble());
            if (propertiesNode.hasNonNull("y"))
                properties.setY(propertiesNode.get("y").asDouble());
            if (propertiesNode.hasNonNull("yllapitaja"))
                properties.setYllapitaja(propertiesNode.get("yllapitaja").asText());
            feature.setProperties(properties);
        }

        // Deserialize geometry
        JsonNode geometryNode = node.get("geometry");
        if (geometryNode != null) {
            Geometry geometry = new Geometry();
            if (geometryNode.hasNonNull("type"))
                geometry.setType(geometryNode.get("type").asText());

            // For coordinates, you'll need to convert the JsonNode to a Double array
            if (geometryNode.hasNonNull("coordinates")) {
                ArrayNode coordinatesNode = (ArrayNode) geometryNode.get("coordinates");
                Double[] coordinates = new Double[coordinatesNode.size()];
                for (int i = 0; i < coordinatesNode.size(); i++) {
                    coordinates[i] = coordinatesNode.get(i).asDouble();
                }
                geometry.setCoordinates(coordinates);
            }

            feature.setGeometry(geometry);
        }

        feature.updateLocationFromGeometry();

        return feature;
    }
}