package dalmineks.domain;

import dalmineks.StoreApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Food_table")
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long itemId;

    private Long storeId;

    private String storeName;

    private Long customerId;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {}

    public static FoodRepository repository() {
        FoodRepository foodRepository = StoreApplication.applicationContext.getBean(
            FoodRepository.class
        );
        return foodRepository;
    }

    public void acceptOrder() {
        OrderAcceted orderAcceted = new OrderAcceted(this);
        orderAcceted.publishAfterCommit();
    }

    public void rejectOrder() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public void startCook() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public void finishCook() {
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static void placeOrder(OrderPlaced orderPlaced) {        
        //주문이 배치 되면 일단 store 에도 일단 "주문 대기"로 상태로 저장한다.
        Food food = new Food();
        food.setOrderId(orderPlaced.getId());
        food.setItemId(orderPlaced.getId());
        food.setStoreId(9000L);
        food.setStoreName("김밥극락");
        food.setCustomerId(orderPlaced.getCustomerId());
        food.setAddress(orderPlaced.getAddress());
        food.setStatus("주문대기");

        repository().save(food);

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(food->{
            
            food // do something
            repository().save(food);


         });
        */

    }

    public static void cancelOrder(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Food food = new Food();
        repository().save(food);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(food->{
            
            food // do something
            repository().save(food);


         });
        */

    }
}
