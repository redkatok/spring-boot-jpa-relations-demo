package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity._3_les_uuid_strategy_demo.UuidBasedJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UuidBasedJpaEntityRepository extends JpaRepository<UuidBasedJpaEntity, UUID> {
}
