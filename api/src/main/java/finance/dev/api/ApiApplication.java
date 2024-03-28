package finance.dev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "finance.dev.api",
                "finance.dev.domain"
        }
)
public class ApiApplication {
    public static void main(String[] args) {
        System.setProperty(
                "spring.config.name",
                "application, application-api, application-common, application-domain");

        SpringApplication.run(ApiApplication.class, args);
    }
}
