package technology.example.reactive.infrastructure.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import technology.example.reactive.domain.model.TechnologyModel;
import technology.example.reactive.infrastructure.adapters.persistence.entity.TechnologyEntity;

@Mapper(componentModel = "spring")
public interface TechnologyEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    TechnologyModel toModel(TechnologyEntity entity);

    TechnologyEntity toEntity(TechnologyModel model);
}
