package io.katkov.spring_boot_jpa_relations_demo.jpa.auto_incremented_by_db_big_int_jpa_entity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._1_les_natural_string_id_demo.NaturalIdStringJpaEntity;
import io.katkov.spring_boot_jpa_relations_demo.entity._2_les_bigint_strategy_identity_demo.AutoIncrementedByDbBigIntJpaEntity;
import io.katkov.spring_boot_jpa_relations_demo.repository.AutoIncrementedByDbBigIntJpaEntityRepository;
import io.katkov.spring_boot_jpa_relations_demo.repository.NaturalIdStringJpaEntityRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AutoIncrementedByDbBigIntJpaEntityRepositoryTest extends BaseJpaTest {

    @Autowired
    private AutoIncrementedByDbBigIntJpaEntityRepository autoIncrementedByDbBigIntJpaEntityRepository;

    @Test
    @DataSet(value = "dataset/jpa_datasets/auto_incremented_by_db_big_int_jpa_entity/findById/init_and_expected.xml", cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/auto_incremented_by_db_big_int_jpa_entity/findById/init_and_expected.xml")
    void testPerson_findById() {
        Optional<AutoIncrementedByDbBigIntJpaEntity> optionalAutoIncrementedByDbBigIntJpaEntity = autoIncrementedByDbBigIntJpaEntityRepository.findById(1L);
        assertThat(optionalAutoIncrementedByDbBigIntJpaEntity.get().getName().equals("autoincrementedbydbbigintjpaentity"));
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/auto_incremented_by_db_big_int_jpa_entity/create/expected.xml")
    void testPerson_save() {
        AutoIncrementedByDbBigIntJpaEntity entity = AutoIncrementedByDbBigIntJpaEntity.builder().name(
            "saveautoincrementedbydbbigintjpaentity").build();
        autoIncrementedByDbBigIntJpaEntityRepository.save(entity);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
