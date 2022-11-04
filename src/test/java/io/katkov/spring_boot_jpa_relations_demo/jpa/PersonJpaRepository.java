package io.katkov.spring_boot_jpa_relations_demo.jpa;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.repository.PersonRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class PersonJpaRepository extends BaseJpaTest {

    @Autowired
    private PersonRepository personRepository;


    @Test
    @DataSet(value = "dataset/jpa_datasets/person/findById/init_and_expected.xml", cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/findById/init_and_expected.xml")
    void testPerson_find() {
        Optional<Person> optionalPerson = personRepository.findById(1L);
        assertThat(optionalPerson.get().getFirstName().equals("findedPersonfirstname"));
    }

    @Test
    @DataSet(cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/create/expected.xml")
    void testPerson_save() {
        Person person = Person.builder().id(1L).firstName("save").lastName("person").build();
        personRepository.save(person);
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/update/init.xml", cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/update/expected.xml")
    void testPerson_update() {
        Person person = Person.builder().id(1L).firstName("michael").lastName("katkov").build();
        personRepository.updatePersonInfo(person.getFirstName(), person.getLastName(), person.getId());
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/delete/init.xml", cleanAfter = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/delete/expected.xml")
    void testPerson_delete() {
        personRepository.deleteById(1L);
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
