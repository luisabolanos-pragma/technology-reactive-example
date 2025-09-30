package technology.example.reactive.domain.use_case;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technology.example.reactive.domain.api.TechnologyRepositoryPort;
import technology.example.reactive.domain.model.TechnologyModel;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class TechnologyGetUseCase {

    private final TechnologyRepositoryPort technologyRepository;

    public Flux<TechnologyModel> listTechnologies() {
        return technologyRepository.findAll();
    }

    public Mono<TechnologyModel> getTechnology(Long id) {
        return technologyRepository.findById(id);
    }

    public Flux<TechnologyModel> listTechnologiesById(List<Long> ids) {
        return technologyRepository.findAllById(ids);
    }
}
