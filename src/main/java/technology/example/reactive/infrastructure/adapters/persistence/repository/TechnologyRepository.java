package technology.example.reactive.infrastructure.adapters.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import technology.example.reactive.infrastructure.adapters.persistence.entity.TechnologyEntity;

import java.util.List;

@Repository
public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {

    Flux<TechnologyEntity> findAllByIdIn(List<Long> ids);
}
