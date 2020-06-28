import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final List<Item> ALL_ITEMS = Arrays.asList(
            new Item("ITEM0001", "Braised chicken", 18.00),
            new Item("ITEM0013", "Chinese hamburger", 6.00),
            new Item("ITEM0022", "Cold noodles", 8.00),
            new Item("ITEM0030", "coca-cola", 2.00)
    );


    public static final List<SalesPromotion> ALL_SALES_PROMOTIONS = Arrays.asList(
            new SalesPromotion("BUY_30_SAVE_6_YUAN", "Deduct 6 yuan when the order reaches 30 yuan", Arrays.asList()),
            new SalesPromotion("50%_DISCOUNT_ON_SPECIFIED_ITEMS", "Half price for certain dishes", Arrays.asList(
                    "ITEM0001", "ITEM0022"
            ))
    );

}
