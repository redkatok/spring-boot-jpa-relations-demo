package io.katkov.spring_boot_jpa_relations_demo.repository.cascaded;

import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.cascaded.LegoConstructorCascaded;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegoConstructorCascadedRepository extends JpaRepository<LegoConstructorCascaded, UUID> {
}