package io.katkov.spring_boot_jpa_relations_demo.jpa.person;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.repository.PersonRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PersonJpaRepository extends BaseJpaTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/findById/init_and_expected.xml", cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/findById/init_and_expected.xml")
    void testPerson_find() {
        Optional<Person> optionalPerson = personRepository.findById(1L);
        assertThat(optionalPerson.get().getFirstName().equals("findedPersonfirstname"));
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/create/expected.xml")
    void testPerson_save() {
        Person person = Person.builder().id(1L).firstName("save").lastName("person").build();
        personRepository.save(person);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/update/init.xml", cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/update/expected.xml")
    void testPerson_update() {
        Person person = Person.builder().id(1L).firstName("michael").lastName("katkov").build();
        personRepository.updatePersonInfo(person.getFirstName(), person.getLastName(), person.getId());
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/delete/init.xml", cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/delete/expected.xml")
    void testPerson_delete() {
        personRepository.deleteById(1L);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
