package io.katkov.spring_boot_jpa_relations_demo.jpa.one_to_many_unidirectional;

import com.github.database.rider.core.api.dataset.DataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.LegoBlock;
import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.LegoConstructor;
import io.katkov.spring_boot_jpa_relations_demo.repository.LegoConstructorJpaRepository;
import io.katkov.spring_boot_jpa_relations_demo.repository.LegoBlockJpaRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class OneToManyUniDirectionalTest extends BaseJpaTest {
    @Autowired
    private LegoConstructorJpaRepository legoConstructorJpaRepository;
    @Autowired
    private LegoBlockJpaRepository legoBlockJpaRepository;

    @DataSet(cleanBefore = true)
    @Test
    void save_constructor_with_presave_legoblocks() {

        LegoConstructor legoConstructor = LegoConstructor.builder().name("Some LegoConstructor").build();

        LegoBlock legoBlock1 = LegoBlock.builder().name("some block 1").build();
        LegoBlock legoBlock2 = LegoBlock.builder().name("some block 2").build();
        LegoBlock legoBlock3 = LegoBlock.builder().name("some block 3").build();

        List<LegoBlock> legoBlocks = List.of(legoBlock1, legoBlock2, legoBlock3);
        legoBlockJpaRepository.saveAll(legoBlocks);
        legoConstructor.setLegoBlocks(legoBlocks);

        legoConstructorJpaRepository.save(legoConstructor);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @DataSet(cleanBefore = true)
    @Test
    void save_constructor_without_presave_legoblocks() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {

            LegoConstructor legoConstructor = LegoConstructor.builder().name("Some LegoConstructor").build();

            LegoBlock legoBlock1 = LegoBlock.builder().name("some block 1").build();
            LegoBlock legoBlock2 = LegoBlock.builder().name("some block 2").build();
            LegoBlock legoBlock3 = LegoBlock.builder().name("some block 3").build();

            List<LegoBlock> legoBlocks = List.of(legoBlock1, legoBlock2, legoBlock3);
            legoConstructor.setLegoBlocks(legoBlocks);

            legoConstructorJpaRepository.save(legoConstructor);

            log.info("комит транзакции флажок");
            TestTransaction.flagForCommit();
            TestTransaction.end();
        });
    }

    @DataSet(cleanBefore = true, value = "dataset/jpa_datasets/one_to_many_unidirectional/findbyid/init.xml")
    @Test
    void findById_Lego_Constructor() {
        Optional<LegoConstructor> legoConstructor = legoConstructorJpaRepository.findById(UUID.fromString("93147495-d109-45e9-bd5a-f5297a16e3e1"));
    }

}
