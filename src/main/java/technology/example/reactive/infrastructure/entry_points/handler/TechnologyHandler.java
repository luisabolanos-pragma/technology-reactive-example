package technology.example.reactive.infrastructure.entry_points.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import technology.example.reactive.domain.use_case.TechnologyGetUseCase;
import technology.example.reactive.domain.use_case.TechnologySaveUseCase;
import technology.example.reactive.infrastructure.entry_points.dto.ResponseDto;
import technology.example.reactive.infrastructure.entry_points.dto.TechnologyDto;
import technology.example.reactive.infrastructure.entry_points.mapper.TechnologyMapper;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechnologyHandler {

    private final TechnologyGetUseCase technologyGetUseCase;
    private final TechnologySaveUseCase technologySaveUseCase;
    private final TechnologyMapper technologyMapper;

    public Mono<ServerResponse> getAllTechnologies(ServerRequest request) {
        return technologyGetUseCase.listTechnologies()
                .collectList()
                .map(res -> technologyMapper.toResponse(res, "Success"))
                .flatMap(responseDto ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(responseDto)
                );
    }

    public Mono<ServerResponse> saveTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyDto.class)
                .map(technologyMapper::toModel)
                .flatMap(technologySaveUseCase::saveTechnology)
                .flatMap(dto -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(technologyMapper.toResponse(dto, "Created")))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));

    }

    public Mono<ServerResponse> getTechnology(ServerRequest request) {
        String id = request.pathVariable("id");
        return technologyGetUseCase.getTechnology(Long.valueOf(id))
                .map(technologyMapper::toDto)
                .map(dto -> new ResponseDto<>("Found", dto))
                .flatMap(responseDto ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(responseDto)
                )
                .switchIfEmpty(
                        ServerResponse.notFound().build()
                );
    }

    public Mono<ServerResponse> listTechnologyById(ServerRequest request) {
        List<Long> id = request.queryParam("ids")
                .map(param -> Arrays.stream(param.split(","))
                        .map(Long::parseLong)
                        .toList())
                .orElse(List.of());

        return technologyGetUseCase.listTechnologiesById(id)
                .collectList()
                .map(res -> technologyMapper.toResponse(res, "Success"))
                .flatMap(responseDto ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(responseDto)
                );
    }
}
