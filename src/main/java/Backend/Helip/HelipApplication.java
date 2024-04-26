package Backend.Helip;

import Backend.Helip.services.FeatureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelipApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HelipApplication.class, args);
        FeatureService featureService = context.getBean(FeatureService.class);
        try {
            featureService.importJson("lipas_kaikki_pisteet.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}