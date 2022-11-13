package io.katkov.spring_boot_jpa_relations_demo.entity._3_les_uuid_strategy_demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "uuid_based_jpa_entity")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class UuidBasedJpaEntity {

    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "pg-uuid"
    )
    @GenericGenerator(
        name = "pg-uuid",
        strategy = "uuid2",
        parameters = @Parameter(
            name = "uuid_gen_strategy_class",
            value = "io.katkov.spring_boot_jpa_relations_demo.entity._3_les_uuid_strategy_demo" +
                ".PostgreSQLUUIDGenerationStrategyForPostgres13AndUpper"
        )
    )
    @Id
    private UUID id;

    private String name;

    @Type(type = "pg-uuid")
    private UUID somecol;
}
