package Backend.Helip.services;

import Backend.Helip.models.Feature;
import Backend.Helip.repositories.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
        try (JsonParser jsonParser = jsonFactory.createParser(new File("./" + path))) {
            JsonToken jsonToken = jsonParser.nextToken(); // Start array token
            if (jsonToken != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected an array");
            }
            while (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                Feature feature = objectMapper.readValue(jsonParser, Feature.class);
                featureRepository.save(feature);
            }
        }
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public List<Feature> getFeaturesWithinRadius(double latitude, double longitude, double radius) {
        List<Feature> allFeatures = featureRepository.findAll();
        List<Feature> nearbyFeatures = new ArrayList<>();

        for (Feature feature : allFeatures) {
            Double[] coordinates = feature.getGeometry().getCoordinates();
            double featureLongitude = coordinates[0].doubleValue();
            double featureLatitude = coordinates[1].doubleValue();

            if (haversine(latitude, longitude, featureLatitude, featureLongitude) <= radius) {
                nearbyFeatures.add(feature);
            }
        }

        return nearbyFeatures;
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

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        int r = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = r * c;

        return distance;
    }
}