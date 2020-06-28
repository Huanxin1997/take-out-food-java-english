import java.util.List;

public class SalesPromotionRepositoryTestImpl implements SalesPromotionRepository {
    @Override
    public List<SalesPromotion> findAll() {
        return TestData.ALL_SALES_PROMOTIONS;
    }
}
