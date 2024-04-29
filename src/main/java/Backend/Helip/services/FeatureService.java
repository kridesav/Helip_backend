package Backend.Helip.services;

import Backend.Helip.models.Feature;
import Backend.Helip.repositories.FeatureRepository;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;

    public void importJson(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonFactory jsonFactory = new JsonFactory();
    GeometryFactory geometryFactory = new GeometryFactory();

    try (JsonParser jsonParser = jsonFactory.createParser(new File("./" + path))) {
        JsonToken jsonToken = jsonParser.nextToken();
        if (jsonToken != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected an object");
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();
            if ("features".equals(fieldName)) {
                jsonParser.nextToken();
                while (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                    JsonNode jsonFeature = objectMapper.readTree(jsonParser);
                    double longitude = jsonFeature.get("geometry").get("coordinates").get(0).asDouble();
                    double latitude = jsonFeature.get("geometry").get("coordinates").get(1).asDouble();
                    Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));

                    Feature feature = objectMapper.convertValue(jsonFeature, Feature.class);
                    feature.setLocation(location);

                    featureRepository.save(feature);
                }
            } else {
                jsonParser.skipChildren();
            }
        }
    }
}

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public List<Feature> getFeaturesWithinRadius(double latitude, double longitude, double radius) {
        String location = "POINT(" + longitude + " " + latitude + ")";
        return featureRepository.findWithinRadius(location, radius);
    }

    public List<Feature> getByName(String name) {
        List<Feature> allFeatures = featureRepository.findAll();
        List<Feature> matchingFeatures = new ArrayList<>();
    
        for (Feature feature : allFeatures) {
            if (feature.getProperties().getNimiFi().contains(name)) {
                matchingFeatures.add(feature);
            }
        }
    
        return matchingFeatures;
    }
}