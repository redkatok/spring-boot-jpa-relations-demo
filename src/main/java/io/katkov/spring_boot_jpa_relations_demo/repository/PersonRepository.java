package io.katkov.spring_boot_jpa_relations_demo.repository;

import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Modifying
    @Query("update Person p set p.firstName = :firstname, p.lastName = :lastName where p.id = :personId")
    void updatePersonInfo(@Param("firstname") String firstname, @Param("lastName") String lastname,
                          @Param("personId") Long personId);

}