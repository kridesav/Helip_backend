package Backend.Helip.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FeatureCollection {
    @JsonProperty("type")
    private String type;

    @JsonProperty("features")
    private List<Feature> features;
    // getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}