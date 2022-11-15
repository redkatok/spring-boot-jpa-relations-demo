package io.katkov.spring_boot_jpa_relations_demo.jpa.one_to_many_unidirectional.cascaded;

import com.github.database.rider.core.api.dataset.DataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.cascaded.LegoBlockUnderCascade;
import io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.cascaded.LegoConstructorCascaded;
import io.katkov.spring_boot_jpa_relations_demo.repository.cascaded.LegoConstructorCascadedRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

@Slf4j
public class OneToManyUnidirectionalCascaded extends BaseJpaTest {

    @Autowired
    private LegoConstructorCascadedRepository legoConstructorCascadedRepository;

    @DataSet(cleanBefore = true)
    @Test
    void save_constructor_without_presave_legoblocks() {

        LegoConstructorCascaded legoConstructor = LegoConstructorCascaded.builder().name("Some LegoConstructor").build();

        LegoBlockUnderCascade legoBlock1 = LegoBlockUnderCascade.builder().name("some block 1").build();
        LegoBlockUnderCascade legoBlock2 = LegoBlockUnderCascade.builder().name("some block 1").build();
        LegoBlockUnderCascade legoBlock3 = LegoBlockUnderCascade.builder().name("some block 1").build();
        List<LegoBlockUnderCascade> legoBlocks = List.of(legoBlock1, legoBlock2, legoBlock3);
        legoConstructor.setLegoBlocks(legoBlocks);

        legoConstructorCascadedRepository.save(legoConstructor);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }
}
