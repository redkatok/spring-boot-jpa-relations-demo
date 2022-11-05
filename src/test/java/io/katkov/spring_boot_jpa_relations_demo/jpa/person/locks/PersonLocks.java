package io.katkov.spring_boot_jpa_relations_demo.jpa.person.locks;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseIT;
import io.katkov.spring_boot_jpa_relations_demo.utils.TransactionalRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

class PersonLocks extends BaseIT {

    @Autowired
    private TransactionalRunner txRunner;

    @Test
    @DataSet(value = "dataset/jpa_datasets/person/optimistic_locking/version/init.xml", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/person/optimistic_locking/version/expected.xml")
    void testPerson_save_optimistic_Version_Locking() {

        Assertions.assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            txRunner.doInTransaction(em1 -> {
                Person p1 = em1.find(Person.class, 1L);
                txRunner.doInTransaction(em2 -> {
                    Person p2 = em2.find(Person.class, 1L);
                    p2.setLastName("Change Tx 2");
                });
                p1.setLastName("Change Tx 1");
            });
        });

    }
}
