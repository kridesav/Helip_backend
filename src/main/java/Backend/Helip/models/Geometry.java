package Backend.Helip.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "geometry")
public class Geometry {
    @JsonProperty("coordinates")
    private Double[] coordinates;
    @Column(name = "geometry_type")
    @JsonProperty("type")
    private String type;
    public Double[] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}