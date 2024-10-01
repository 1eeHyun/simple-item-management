package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {

        // given
        Item item = new Item("itemA", 10.0, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void findAll() {

        // given
        Item item1 = new Item("itemA", 10.0, 10);
        Item item2 = new Item("itemB", 20.0, 20);
        Item item3 = new Item("itemC", 30.0, 30);

        // when
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).contains(item1);
        assertThat(items).contains(item2);
        assertThat(items).contains(item3);
        assertThat(items.size()).isEqualTo(3);
    }

    @Test
    void updateItem() {

        // give
        Item item1 = new Item("itemA", 10.0, 10);

        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        // when
        Item item2 = new Item("itemB", 20.0, 20);
        itemRepository.update(itemId, item2);

        // then
        Item foundItem = itemRepository.findById(itemId);
        assertThat(foundItem).isEqualTo(item2);
    }
}