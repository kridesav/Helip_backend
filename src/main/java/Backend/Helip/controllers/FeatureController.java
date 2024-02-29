package Backend.Helip.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Backend.Helip.models.Feature;
import Backend.Helip.services.FeatureService;

@RestController
@RequestMapping("/api/places")
public class FeatureController {

    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping
    public List<Feature> getAllFeatures() {
        return featureService.getAllFeatures();
    }

    @GetMapping("/nearby")
    public List<Feature> getNearbyFeatures(@RequestParam double latitude, @RequestParam double longitude) {
        return featureService.getFeaturesWithinRadius(latitude, longitude, 5);
    }
}