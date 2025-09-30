package technology.example.reactive.domain.use_case;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import technology.example.reactive.domain.api.TechnologyRepositoryPort;
import technology.example.reactive.domain.model.TechnologyModel;

@AllArgsConstructor
@Slf4j
public class TechnologySaveUseCase {

    private final TechnologyRepositoryPort technologyRepository;

    public Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel) {
        if (technologyModel.name().length() > 50 || technologyModel.description().length() > 90) {
            return Mono.error(new IllegalArgumentException("Nombre o descripción demasiado largos"));
        }
        return technologyRepository.save(technologyModel);
    }
}
