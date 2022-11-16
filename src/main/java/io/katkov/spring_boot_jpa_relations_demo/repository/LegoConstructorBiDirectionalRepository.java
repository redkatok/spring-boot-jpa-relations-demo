package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional.LegoBlockBidirectional;
import io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional.LegoConstructorBiDirectional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LegoConstructorBiDirectionalRepository extends JpaRepository<LegoConstructorBiDirectional, UUID> {

    List<LegoBlockBidirectional> findByName(String name);


    @Query(value = "select l from LegoBlockBidirectional l where l.name=:name")
    List<LegoConstructorBiDirectional> findByNameCustom(String name);
}