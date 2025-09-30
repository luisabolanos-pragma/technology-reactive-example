package technology.example.reactive.infrastructure.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technology.example.reactive.domain.api.TechnologyRepositoryPort;
import technology.example.reactive.domain.model.TechnologyModel;
import technology.example.reactive.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
import technology.example.reactive.infrastructure.adapters.persistence.repository.TechnologyRepository;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class TechnologyPersistenceAdapter implements TechnologyRepositoryPort {

    private final TechnologyRepository technologyRepository;
    private final TechnologyEntityMapper mapper;

    @Override
    public Flux<TechnologyModel> findAll() {
        return technologyRepository.findAll().map(mapper::toModel);
    }

    @Override
    public Mono<TechnologyModel> save(TechnologyModel data) {
        return technologyRepository
                .save(mapper.toEntity(data))
                .map(mapper::toModel);
    }

    @Override
    public Mono<TechnologyModel> findById(Long id) {
        return technologyRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public Flux<TechnologyModel> findAllById(List<Long> id) {
        return technologyRepository.findAllByIdIn(id).map(mapper::toModel);
    }
}
