package io.katkov.spring_boot_jpa_relations_demo.entity._1_les_natural_string_id_demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "natural_id_string_jpa_entity")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class NaturalIdStringJpaEntity {

    @Id
    private String id;

    private String name;
}
