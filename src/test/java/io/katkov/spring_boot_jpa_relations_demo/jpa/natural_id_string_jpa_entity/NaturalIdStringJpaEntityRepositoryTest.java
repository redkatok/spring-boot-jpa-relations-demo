package io.katkov.spring_boot_jpa_relations_demo.jpa.natural_id_string_jpa_entity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._1_les_natural_string_id_demo.NaturalIdStringJpaEntity;
import io.katkov.spring_boot_jpa_relations_demo.repository.NaturalIdStringJpaEntityRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class NaturalIdStringJpaEntityRepositoryTest extends BaseJpaTest {

    @Autowired
    private NaturalIdStringJpaEntityRepository naturalIdStringJpaEntityRepository;

    @Test
    @DataSet(value = "dataset/jpa_datasets/naturalidstringjpaentity/findById/init_and_expected.xml", cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/naturalidstringjpaentity/findById/init_and_expected.xml")
    void testPerson_findById() {
        Optional<NaturalIdStringJpaEntity> optionalPerson = naturalIdStringJpaEntityRepository.findById("naturalid");
        assertThat(optionalPerson.get().getName().equals("naturalidstringjpaentity"));
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/naturalidstringjpaentity/create/expected.xml")
    void testPerson_save() {
        NaturalIdStringJpaEntity entity = NaturalIdStringJpaEntity.builder().id("savednaturalidstringjpaentity").name(
            "savenaturalidstringjpaentity").build();
        naturalIdStringJpaEntityRepository.save(entity);
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
