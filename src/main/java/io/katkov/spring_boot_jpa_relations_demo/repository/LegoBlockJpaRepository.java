package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.LegoBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegoBlockJpaRepository extends JpaRepository<LegoBlock, UUID> {
}