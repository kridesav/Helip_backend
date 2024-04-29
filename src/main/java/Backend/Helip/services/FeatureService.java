package Backend.Helip.services;

import Backend.Helip.models.Feature;
import Backend.Helip.repositories.FeatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteAllFeatures() {
        featureRepository.deleteAll();
    }

    public void importJson(String path) throws IOException {
        deleteAllFeatures();
        ObjectMapper objectMapper = new ObjectMapper();
        
        try (JsonParser jsonParser = objectMapper.createParser(new File("./" + path))) {
            JsonToken jsonToken = jsonParser.nextToken();
            if (jsonToken != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected an object");
            }
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("features".equals(fieldName)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                        Feature feature = objectMapper.readValue(jsonParser, Feature.class);
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