package br.com.magna.gateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public CommandLineRunner apis(
            RouteDefinitionLocator routeDefinitionLocator,
            SwaggerUiConfigParameters swaggerUiParameters
    ) {
        List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions()
                .collectList()
                .block();
        assert definitions != null;
        return args -> definitions.stream()
                .filter(routeDefinition ->
                        routeDefinition.getId()
                                .matches("ReactiveCompositeDiscoveryClient_.*")
                ).forEach(routeDefinition -> {
                            String name = routeDefinition.getId()
                                    .replaceAll("ReactiveCompositeDiscoveryClient_", "")
                                    .toLowerCase();
                            swaggerUiParameters.addGroup(name, name.replaceAll("-api", ""));
                        }
                );
    }

}
