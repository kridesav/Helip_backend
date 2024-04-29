package Backend.Helip.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseToken;

import Backend.Helip.models.Feature;
import Backend.Helip.services.FeatureService;
import Backend.Helip.services.FirebaseService;

@RestController
@RequestMapping("/api/places")
public class FeatureController {

    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping
    public ResponseEntity<List<Feature>> getAllFeatures(@RequestHeader("Authorization") String idToken) {
        FirebaseToken decodedToken = firebaseService.decodeToken(idToken.replace("Bearer ", ""));
        if (decodedToken != null) {
            List<Feature> features = featureService.getAllFeatures();
            return ResponseEntity.ok(features);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Feature>> getNearbyFeatures(@RequestParam double latitude,
            @RequestParam double longitude, @RequestHeader("Authorization") String idToken) {
        FirebaseToken decodedToken = firebaseService.decodeToken(idToken.replace("Bearer ", ""));
        if (decodedToken != null) {
            List<Feature> features = featureService.getFeaturesWithinRadius(latitude, longitude, 5000);
            return ResponseEntity.ok(features);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Feature>> getByName(@RequestParam String param,
            @RequestHeader("Authorization") String idToken) {
        FirebaseToken decodedToken = firebaseService.decodeToken(idToken.replace("Bearer ", ""));
        if (decodedToken != null) {
            List<Feature> features = featureService.getByName(param);
            return ResponseEntity.ok(features);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}