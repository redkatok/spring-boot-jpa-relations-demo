package io.katkov.spring_boot_jpa_relations_demo.jpa.uuid_based_jpa_entity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._3_les_uuid_strategy_demo.UuidBasedJpaEntity;
import io.katkov.spring_boot_jpa_relations_demo.repository.UuidBasedJpaEntityRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

@Slf4j
public class UuidBasedJpaEntityJpaRepositoryTest extends BaseJpaTest {

    @Autowired
    private UuidBasedJpaEntityRepository uuidBasedJpaEntityRepository;

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/uuid_based_jpa_entity/create/expected.xml")
    void test_UuidBasedJpaEntity_save() {
        UuidBasedJpaEntity entity = UuidBasedJpaEntity.builder().name("saveduuidentity").build();
        uuidBasedJpaEntityRepository.save(entity);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }
}
