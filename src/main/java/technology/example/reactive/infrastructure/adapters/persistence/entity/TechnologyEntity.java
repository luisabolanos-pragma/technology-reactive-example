package technology.example.reactive.infrastructure.adapters.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "technology")
@Getter
@Setter
@RequiredArgsConstructor
public class TechnologyEntity {

    @Id
    private Long id;
    private String name;
    private String description;
}
