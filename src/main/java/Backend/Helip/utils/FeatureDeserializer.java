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
    feature.setId(node.get("id").asLong());
    feature.setType(node.get("type").asText());

    // Deserialize properties
    JsonNode propertiesNode = node.get("properties");
    Properties properties = new Properties();
    properties.setId(propertiesNode.get("id").asLong());
    properties.setKatuosoite(propertiesNode.get("katuosoite").asText());
    properties.setKuntaNimi(propertiesNode.get("kunta_nimi").asText());
    properties.setKuntanumer(propertiesNode.get("kuntanumer").asInt());
    properties.setLisatieto(propertiesNode.get("lisatieto_").asText());
    properties.setMuokattuV(propertiesNode.get("muokattu_v").asText());
    properties.setNimiFi(propertiesNode.get("nimi_fi").asText());
    properties.setNimiSe(propertiesNode.get("nimi_se").asText());
    properties.setOmistaja(propertiesNode.get("omistaja").asText());
    properties.setPisteJson(propertiesNode.get("piste_json").asText());
    properties.setPostinumer(propertiesNode.get("postinumer").asText());
    properties.setPostitoimi(propertiesNode.get("postitoimi").asText());
    properties.setPuhelinnum(propertiesNode.get("puhelinnum").asText());
    properties.setSahkoposti(propertiesNode.get("sahkoposti").asText());
    properties.setSijaintiI(propertiesNode.get("sijainti_i").asInt());
    properties.setTyyppiN1(propertiesNode.get("tyyppi_n_1").asText());
    properties.setTyyppiN2(propertiesNode.get("tyyppi_n_2").asText());
    properties.setTyyppiNim(propertiesNode.get("tyyppi_nim").asText());
    properties.setTyyppikood(propertiesNode.get("tyyppikood").asInt());
    properties.setWww(propertiesNode.get("www").asText());
    properties.setX(propertiesNode.get("x").asDouble());
    properties.setY(propertiesNode.get("y").asDouble());
    properties.setYllapitaja(propertiesNode.get("yllapitaja").asText());
    feature.setProperties(properties);

    // Deserialize geometry
    JsonNode geometryNode = node.get("geometry");
    Geometry geometry = new Geometry();
    geometry.setType(geometryNode.get("type").asText());

    // For coordinates, you'll need to convert the JsonNode to a Double array
    ArrayNode coordinatesNode = (ArrayNode) geometryNode.get("coordinates");
    Double[] coordinates = new Double[coordinatesNode.size()];
    for (int i = 0; i < coordinatesNode.size(); i++) {
        coordinates[i] = coordinatesNode.get(i).asDouble();
    }
    geometry.setCoordinates(coordinates);

    feature.setGeometry(geometry);

    feature.updateLocationFromGeometry();

    return feature;
}
}