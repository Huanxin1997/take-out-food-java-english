import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;
    private static final String BASIC_MONEY_UNIT = "yuan";
    private static final String BASE_UNIT_SPLIT = " × ";
    private double resultMoney;
    private Map<String, Item> itemMap;
    private Map<String, Item> orderItemMap;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }

    public String bestCharge(List<String> inputs) {
        //TODO: write code here
        StringBuilder res = new StringBuilder("============= Order details =============\n");
        List<Item> itemList = itemRepository.findAll();

        itemMap = new HashMap<>();
        orderItemMap = new HashMap<>();
        for (Item item : itemList) {
            itemMap.put(item.getId(), item);
        }

        double moneyWithoutDiscount = 0;
        for (String s : inputs) {
            String[] split = s.split(BASE_UNIT_SPLIT);
            moneyWithoutDiscount += itemMap.get(split[0]).getPrice() * Integer.valueOf(split[1]);
            res.append(itemMap.get(split[0]).getName())
                    .append(BASE_UNIT_SPLIT)
                    .append(Integer.valueOf(split[1]))
                    .append(" = ")
                    .append((int)(itemMap.get(split[0]).getPrice() * Integer.valueOf(split[1])))
                    .append(" ").append(BASIC_MONEY_UNIT + "\n");
            orderItemMap.put(split[0], itemMap.get(split[0]));
            resultMoney += (itemMap.get(split[0]).getPrice() * Integer.valueOf(split[1]));
        }
        List<SalesPromotion> salesPromotionList = salesPromotionRepository.findAll();
        res.append(chooseCharge(salesPromotionList, moneyWithoutDiscount));

        res.append("-----------------------------------\n")
                .append("Total: ").append((int)resultMoney).append(" ").append(BASIC_MONEY_UNIT).append("\n")
                .append("===================================");
        return res.toString();
    }

    public String chooseCharge(List<SalesPromotion> salesPromotionList, double moneyWithoutDiscount) {

        StringBuilder res = new StringBuilder("-----------------------------------\n").append("Promotion used:\n");
        StringBuilder str = new StringBuilder();

        SalesPromotion choosePro = null;
        for (SalesPromotion pro : salesPromotionList) {
            if ("BUY_30_SAVE_6_YUAN".equals(pro.getType()) && moneyWithoutDiscount >= 30) {
                if (Double.compare(resultMoney, moneyWithoutDiscount - 6) > 0) {
                    resultMoney = moneyWithoutDiscount - 6;
                    str = new StringBuilder();
                    str.append("Deduct 6 yuan when the order reaches 30 yuan, saving 6 yuan\n");
                }
            } else if ("50%_DISCOUNT_ON_SPECIFIED_ITEMS".equals(pro.getType())) {
                double saveMoney = 0;
                boolean whetherBreak = false;
                for (String item : pro.getRelatedItems()) {
                    if (orderItemMap.get(item) == null) {
                        whetherBreak = true;
                    }
                    saveMoney += itemMap.get(item).getPrice() / 2;
                }
                if (!whetherBreak && Double.compare(resultMoney, moneyWithoutDiscount * 0.5) > 0) {
                    resultMoney = moneyWithoutDiscount - saveMoney;
                    str = new StringBuilder();
                    str.append(pro.getDisplayName());
                    str.append(" (");

                    int flag = 0;
                    for (String item : pro.getRelatedItems()) {
                        if (flag == 0) {
                            str.append(itemMap.get(item).getName());
                            flag = 1;
                        } else {
                            str.append(", ").append(itemMap.get(item).getName());
                        }
                    }
                    str.append("), saving ").append((int)saveMoney).append(" yuan\n");
                }
            }
        }

        if (str.toString().isEmpty()){
            return str.toString();
        }else {
            return res.append(str).toString();
        }
    }
}
