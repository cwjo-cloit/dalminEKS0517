package dalmineks.domain;

import dalmineks.FrontApplication;
import dalmineks.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long itemId;

    private String itemName;

    private Long customerId;

    private Integer price;

    private String status;

    private String address;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void cnacel() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public void deliveryComplete() {
        FoodDeliveryCompleted foodDeliveryCompleted = new FoodDeliveryCompleted(
            this
        );
        foodDeliveryCompleted.publishAfterCommit();
    }

    public static void rejectOrder(OrderRejected orderRejected) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderRejected.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }

    public static void rejectOrder(CookStarted cookStarted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(cookStarted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
