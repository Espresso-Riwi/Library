import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    Item getItemById(int id);
    List<Item> getAllItems();
    void updateItem(Item item);
    void deleteItem(int id);
}