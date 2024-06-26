package Backend.Helip.repositories;

import Backend.Helip.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    @Query(value = "SELECT * FROM features f WHERE ST_DWithin(CAST(f.location AS geography), CAST(:location AS geography), :radius)", nativeQuery = true)
    List<Feature> findWithinRadius(@Param("location") String location, @Param("radius") double radius);
}