package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByName(String name);

    @Query(value = "select i from Item i join fetch  i.box where i.name=:name")
    List<Item> findByNameCustom(String name);

    @EntityGraph(
        type = EntityGraph.EntityGraphType.FETCH,
        attributePaths = {
            "box"
        }
    )
    List<Item> findAll();
}
