package Backend.Helip.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Backend.Helip.models.Feature;

import java.io.IOException;

public class FeatureDeserializer extends JsonDeserializer<Feature> {
    public Feature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.getCodec().readTree(p);
        Feature feature = mapper.treeToValue(node, Feature.class);
    
        feature.updateLocationFromGeometry();
    
        return feature;
    }
}