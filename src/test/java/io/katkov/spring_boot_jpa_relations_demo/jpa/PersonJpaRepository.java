package io.katkov.spring_boot_jpa_relations_demo.jpa;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.repository.PersonRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class PersonJpaRepository extends BaseJpaTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/create_person_dataset.xml", cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/create_person_dataset.xml")
    void testPerson_find() {
        Optional<Person> optionalPerson = personRepository.findById(1L);
        assertThat(optionalPerson.get().getFirstName().equals("findedPersonfirstname"));
    }

    @Test
    @DataSet(cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/expected_person_dataset.xml")
    void testPerson_save() {
        Person person = Person.builder().id(2L).firstName("mike").lastName("katkov").build();
        personRepository.save(person);
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
