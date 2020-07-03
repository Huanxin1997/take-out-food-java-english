import java.util.List;

public class ItemRepositoryTestImpl implements ItemRepository{
    @Override
    public List<Item> findAll() {
        return TestData.ALL_ITEMS;
    }
}
