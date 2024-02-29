package Backend.Helip.services;

import Backend.Helip.models.Feature;
import Backend.Helip.models.FeatureCollection;
import Backend.Helip.repositories.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;

    public void importJson(String path) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("./" + path));
        ObjectMapper objectMapper = new ObjectMapper();
        FeatureCollection featureCollection = objectMapper.readValue(jsonData, FeatureCollection.class);
        for (Feature feature : featureCollection.getFeatures()) {
            featureRepository.save(feature);
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