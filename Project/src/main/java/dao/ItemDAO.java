import java.util.List;

public interface ItemDAO {
    Item getItemById(int id);
    List<Item> getAllItems();
}