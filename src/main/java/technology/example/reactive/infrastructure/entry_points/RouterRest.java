package technology.example.reactive.infrastructure.entry_points;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import technology.example.reactive.infrastructure.entry_points.handler.TechnologyHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(TechnologyHandler technologyHandler) {
        return route(GET("/technologies/get"), technologyHandler::getAllTechnologies)
                .andRoute(POST("/technologies/save"), technologyHandler::saveTechnology)
                .andRoute(GET("/technologies/get/{id}"), technologyHandler::getTechnology)
                .andRoute(GET("/technologies"), technologyHandler::listTechnologyById);
    }
}
