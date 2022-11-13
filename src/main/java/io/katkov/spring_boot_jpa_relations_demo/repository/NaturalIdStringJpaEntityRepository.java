package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity._1_les_natural_string_id_demo.NaturalIdStringJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaturalIdStringJpaEntityRepository extends JpaRepository<NaturalIdStringJpaEntity, String> {
}
