package Backend.Helip.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import Backend.Helip.models.Feature;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class FeatureDeserializer extends JsonDeserializer<Feature> {
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Feature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        Feature feature = mapper.treeToValue(p.readValueAsTree(), Feature.class);

        if (feature.getGeometry() != null) {
            Double[] coordinates = feature.getGeometry().getCoordinates();
            if (coordinates.length > 1) {
                Point location = geometryFactory.createPoint(new Coordinate(coordinates[0], coordinates[1]));
                feature.setLocation(location);
            }
        }
        return feature;
    }
}