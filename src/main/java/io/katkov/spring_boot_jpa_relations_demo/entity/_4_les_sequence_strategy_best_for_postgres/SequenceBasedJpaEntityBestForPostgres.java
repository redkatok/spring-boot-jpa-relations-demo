package io.katkov.spring_boot_jpa_relations_demo.entity._4_les_sequence_strategy_best_for_postgres;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "sequence_based_jpa_entity_best_for_postgres")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class SequenceBasedJpaEntityBestForPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_based_jpa_entity_best_for_postgres_gen")
    @SequenceGenerator(name = "sequence_based_jpa_entity_best_for_postgres_gen", sequenceName =
        "sequence_based_jpa_entity_best_for_postgres_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;
}
