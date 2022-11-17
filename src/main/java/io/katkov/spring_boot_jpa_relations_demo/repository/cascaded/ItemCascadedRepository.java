package io.katkov.spring_boot_jpa_relations_demo.repository.cascaded;

import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded.ItemWithCascadeAllToBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCascadedRepository extends JpaRepository<ItemWithCascadeAllToBox,Long> {
}
