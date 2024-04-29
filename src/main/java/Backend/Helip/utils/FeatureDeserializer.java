package Backend.Helip.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import Backend.Helip.models.Feature;

import java.io.IOException;

public class FeatureDeserializer extends JsonDeserializer<Feature> {
    private final ObjectMapper mapper = new ObjectMapper();

    public FeatureDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Feature.class, this);
        mapper.registerModule(module);
    }

    @Override
    public Feature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Feature feature = mapper.treeToValue(node, Feature.class);
        feature.updateLocationFromGeometry();
        return feature;
    }
}