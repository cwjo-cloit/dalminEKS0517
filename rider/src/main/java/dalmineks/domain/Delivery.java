package dalmineks.domain;

import dalmineks.RiderApplication;
import dalmineks.domain.FoodDeliveryPicked;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long storeId;

    private Long itemId;

    private Long customerId;

    private String address;

    private Long riderId;

    private String riderName;

    private String status;

    @PostPersist
    public void onPostPersist() {
        FoodDeliveryPicked foodDeliveryPicked = new FoodDeliveryPicked(this);
        foodDeliveryPicked.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pickUpFood(PickUpFoodCommand pickUpFoodCommand) {}

    public static void deliveryRequest(CookFinished cookFinished) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(cookFinished.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }

    public static void deliveryFinish(
        FoodDeliveryCompleted foodDeliveryCompleted
    ) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(foodDeliveryCompleted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
}
