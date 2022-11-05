package io.katkov.spring_boot_jpa_relations_demo.jpa;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.repository.PersonRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import beans.utility.jpa.TransactionalRunner;
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

    @Autowired
    private TransactionalRunner txRunner;


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

//    @Test
//    @DataSet(value = "dataset/jpa_datasets/person/optimistic_locking/version/init.xml",cleanAfter = true)
//    @ExpectedDataSet(value = "dataset/jpa_datasets/person/optimistic_locking/version/expected.xml")
    void testPerson_save_optimistic_Version_Locking() {
//        Person person = Person.builder().id(1L).firstName("user0").lastName("nochange").build();
//        Person savedPerson = personRepository.save(person);
//        TestTransaction.flagForCommit();
//        TestTransaction.end();
//
//        txRunner.doInTransaction(em1 -> {
//            Person p1 = em1.find(Person.class, 1L);
//            txRunner.doInTransaction(em2 -> {
//                Person p2 = em2.find(Person.class, 1L);
//                p2.setLastName("Change Tx 2");
//                TestTransaction.flagForCommit();
//                TestTransaction.end();
//            });
//            p1.setLastName("Change Tx 1");
//            TestTransaction.flagForCommit();
//            TestTransaction.end();
//        });

    }

}
