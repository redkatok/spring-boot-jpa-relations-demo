package io.katkov.spring_boot_jpa_relations_demo.jpa.one_to_many_bidirectional;

import com.github.database.rider.core.api.dataset.DataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional.LegoBlockBidirectional;
import io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional.LegoConstructorBiDirectional;
import io.katkov.spring_boot_jpa_relations_demo.repository.LegoConstructorBiDirectionalRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

@Slf4j
public class OneToManyBidirectional extends BaseJpaTest {

    @Autowired
    private LegoConstructorBiDirectionalRepository legoConstructorBiDirectionalRepository;

    /**
     * все сохранится но внещние ключи в легоблоках не проставятся
     */
    @DataSet(cleanBefore = true)
    @Test
    void save_constructor_with_legoblocks() {

        LegoConstructorBiDirectional legoConstructor = LegoConstructorBiDirectional.builder().name("Some LegoConstructor").build();

        LegoBlockBidirectional legoBlock1 = LegoBlockBidirectional.builder().name("some block 1").build();
        LegoBlockBidirectional legoBlock2 = LegoBlockBidirectional.builder().name("some block 2").build();
        LegoBlockBidirectional legoBlock3 = LegoBlockBidirectional.builder().name("some block 3").build();
        List<LegoBlockBidirectional> legoBlocks = List.of(legoBlock1, legoBlock2, legoBlock3);
        legoConstructor.setLegoBlocks(legoBlocks);

        legoConstructorBiDirectionalRepository.save(legoConstructor);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    /**
     * все сохранится но внешние ключи в легоблоках не проставятся
     */
    @DataSet(cleanBefore = true)
    @Test
    void save_constructor_with_legoblocks_with_fk_fulfilled() {

        LegoConstructorBiDirectional legoConstructor = LegoConstructorBiDirectional.builder().name("Some LegoConstructor").build();

        LegoBlockBidirectional legoBlock1 = LegoBlockBidirectional.builder().name("some block 1").build();
        LegoBlockBidirectional legoBlock2 = LegoBlockBidirectional.builder().name("some block 2").build();
        LegoBlockBidirectional legoBlock3 = LegoBlockBidirectional.builder().name("some block 3").build();
        List<LegoBlockBidirectional> legoBlocks = List.of(legoBlock1, legoBlock2, legoBlock3);
        legoConstructor.addLegoBlocks(legoBlocks);

        legoConstructorBiDirectionalRepository.save(legoConstructor);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }
}
