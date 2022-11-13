package io.katkov.spring_boot_jpa_relations_demo.jpa.sequence_based_jpa_entity_best_for_postgres;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity.Person;
import io.katkov.spring_boot_jpa_relations_demo.entity._4_les_sequence_strategy_best_for_postgres.SequenceBasedJpaEntityBestForPostgres;
import io.katkov.spring_boot_jpa_relations_demo.repository.SequenceBasedJpaEntityBestForPostgresRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

@Slf4j
public class SequenceBasedJpaEntityBestForPostgresRepositoryTest extends BaseJpaTest {

    @Autowired
    private SequenceBasedJpaEntityBestForPostgresRepository sequenceBasedJpaEntityBestForPostgresRepository;

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/sequence_based_jpa_entity_best_for_postgres/create/expected.xml")
    void testPerson_save() {
        SequenceBasedJpaEntityBestForPostgres entity =
            SequenceBasedJpaEntityBestForPostgres.builder().name("savesequencebasedentity").build();
        sequenceBasedJpaEntityBestForPostgresRepository.save(entity);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }
}
