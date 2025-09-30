package technology.example.reactive.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import technology.example.reactive.domain.api.TechnologyRepositoryPort;
import technology.example.reactive.domain.use_case.TechnologyGetUseCase;
import technology.example.reactive.domain.use_case.TechnologySaveUseCase;


@Configuration
public class UseCasesConfig {

    @Bean
    public TechnologyGetUseCase technologyListUseCase(TechnologyRepositoryPort repository) {
        return new TechnologyGetUseCase(repository);
    }

    @Bean
    public TechnologySaveUseCase technologyNewUseCase(TechnologyRepositoryPort repository) {
        return new TechnologySaveUseCase(repository);
    }
}
