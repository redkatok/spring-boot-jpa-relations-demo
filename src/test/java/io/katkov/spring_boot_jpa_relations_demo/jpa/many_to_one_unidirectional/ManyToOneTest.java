package io.katkov.spring_boot_jpa_relations_demo.jpa.many_to_one_unidirectional;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.Box;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.Item;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded.BoxUnderCascadeAll;
import io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded.ItemWithCascadeAllToBox;
import io.katkov.spring_boot_jpa_relations_demo.repository.BoxRepository;
import io.katkov.spring_boot_jpa_relations_demo.repository.ItemRepository;
import io.katkov.spring_boot_jpa_relations_demo.repository.cascaded.ItemCascadedRepository;
import io.katkov.spring_boot_jpa_relations_demo.support.BaseJpaTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class ManyToOneTest extends BaseJpaTest {

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private ItemRepository itemRepository;



    /**
     * будет эксепшн так как box на момент установки связи отсутствует в бд
     */
    @Test
    void save_Box_And_items_incorrect_saving() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            Box box = Box.builder().name("box1").build();
            Item item = Item.builder().name("item1").build();
            item.setBox(box);

            itemRepository.save(item);

            log.info("комит транзакции флажок");
            TestTransaction.flagForCommit();
            TestTransaction.end();
        });
    }

    @Test
    void save_Box_And_items() {
        Box box = Box.builder().name("box1").build();
        Item item = Item.builder().name("item1").build();

        boxRepository.save(box);
        item.setBox(box);
        itemRepository.save(item);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/create/init.xml",
        cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/create/expected.xml")
    void save_Box_And_items_with_existing_box() {
        UUID uuid = UUID.fromString("a401d079-d4ff-42b1-a34b-78af2dbb4aa8");
        Item item = Item.builder().name("item1").build();
        Box box = Box.builder().id(uuid).name("box1").build();

        item.setBox(box);
        itemRepository.save(item);

        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/findbyid/box/init.xml",
        cleanBefore = true)
    @ExpectedDataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/create/expected.xml")
    void findById_box() {
        Optional<Box> byId = boxRepository.findById(UUID.fromString("a401d079-d4ff-42b1-a34b-78af2dbb4aa9"));
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    @DataSet(value = "dataset/jpa_datasets/many_to_one_unidirectional/findbyid/item/init.xml",
        cleanBefore = true)
    void findById_item() {
        itemRepository.findById(UUID.fromString("a401d079-d4ff-42b1-a34b-78af2dbb4aa9"));
        log.info("комит транзакции флажок");
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }








}
