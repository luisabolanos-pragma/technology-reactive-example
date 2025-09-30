package technology.example.reactive.infrastructure.entry_points.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import technology.example.reactive.domain.model.TechnologyModel;
import technology.example.reactive.infrastructure.entry_points.dto.ResponseDto;
import technology.example.reactive.infrastructure.entry_points.dto.TechnologyDto;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    TechnologyDto toDto(TechnologyModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    TechnologyModel toModel(TechnologyDto dto);

    @Mapping(source = "data", target = "data")
    @Mapping(source = "message", target = "message")
    ResponseDto toResponse(Object data, String message);
}
