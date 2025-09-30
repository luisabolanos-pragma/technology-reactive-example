package technology.example.reactive.domain.api;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technology.example.reactive.domain.model.TechnologyModel;

import java.util.List;

public interface TechnologyRepositoryPort {

    Flux<TechnologyModel> findAll();

    Mono<TechnologyModel> save(TechnologyModel technology);

    Mono<TechnologyModel> findById(Long id);

    Flux<TechnologyModel> findAllById(List<Long> id);
}
