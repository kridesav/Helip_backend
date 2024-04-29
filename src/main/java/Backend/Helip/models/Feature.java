package Backend.Helip.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import Backend.Helip.utils.FeatureDeserializer;



@Entity
@Table(name = "features")
@JsonDeserialize(using = FeatureDeserializer.class)
public class Feature {
    private static final GeometryFactory geometryFactory = new GeometryFactory();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;

    @Embedded
    @JsonProperty("geometry")
    private Geometry geometry;

    @Embedded
    @JsonProperty("properties")
    private Properties properties;
    
    @JsonProperty("type")
    private String type;

    @JsonGetter("location")
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
    
    @JsonGetter("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void updateLocationFromGeometry() {
        if (this.geometry != null && this.geometry.getCoordinates() != null) {
            Double[] coordinates = this.geometry.getCoordinates();
            if (coordinates.length > 1) {
                Point location = geometryFactory.createPoint(new Coordinate(coordinates[0], coordinates[1]));
                this.setLocation(location);
            }
        }
    }
}
