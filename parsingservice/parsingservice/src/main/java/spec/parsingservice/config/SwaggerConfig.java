package spec.parsingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${project.title}") String title,
                                 @Value("${project.version}") String version,
                                 @Value("${project.description}") String description) {
        return new OpenAPI()
                .info(new Info()
                        .title(String.format("%s API", title))
                        .version(version)
                        .description(description)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("GNU GENERAL PUBLIC LICENSE v.3").url("http://springdoc.org"))
                        .contact(new Contact().name("Element Lab support").email("support@element-lab.ru")));
    }
}
