package com.babalola.smartparkingapplication.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfiguration {

    public static final String[] SWAGGER_AUTH_WHITELIST = new String[]{"/**/swagger-ui.html", "/**/webjars/**", "/**/swagger-resources", "/**/swagger-resources/**", "/**/v2/api-docs", "/**/configuration/ui", "/**/configuration/security", "/**/swagger-ui/**", "/**/v3/api-docs/**"};

    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Parking Application")
                        .version("1.0.0").description("This is a smart parking application developed as part of the requirement for a Msc in Computing")
                        .license(new License().name("Babalola Opeyemi Daniel").url("http://babalola.dev"))
                        .contact(new Contact().name("Babalola Opeyemi Daniel").email("babalolaopedaniel@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                .description("Smart Parking Application Documentation")
                .url("https:/babalola.dev"));
    }
}
