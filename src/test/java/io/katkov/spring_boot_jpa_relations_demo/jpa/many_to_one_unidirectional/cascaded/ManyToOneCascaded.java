package io.katkov.spring_boot_jpa_relations_demo.jpa.many_to_one_unidirectional.cascaded;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded.BoxUnderCascadeAll;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded.ItemWithCascadeAllToBox;
import io.katkov.spring_boot_jpa_relations_demo.repository.cascaded.ItemCascadedRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

@Slf4j
public class ManyToOneCascaded extends BaseJpaTest {

    @Autowired
    private ItemCascadedRepository itemCascadedRepository;

    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/cascaded/expected.xml")
    @Test
    void save_box_via_cascade_in_item() {
        BoxUnderCascadeAll box = BoxUnderCascadeAll.builder().name("box1").build();
        ItemWithCascadeAllToBox item = ItemWithCascadeAllToBox.builder().name("item1").box(box).build();

        itemCascadedRepository.save(item);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }
}
