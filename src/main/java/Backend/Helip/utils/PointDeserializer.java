package Backend.Helip.utils;

import org.locationtech.jts.geom.Coordinate;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonParser;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.GeometryFactory;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PointDeserializer extends JsonDeserializer<Point> {
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double longitude = node.get("coordinates").get(0).asDouble();
        double latitude = node.get("coordinates").get(1).asDouble();
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}