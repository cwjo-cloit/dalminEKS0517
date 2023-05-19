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
    public void onPostPersist() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pickUpFood(PickUpFoodCommand pickUpFoodCommand) {
        //배송대기 중인 음식을 선택하여 배달을 시작
        FoodDeliveryPicked foodDeliveryPicked = new FoodDeliveryPicked(this);
        foodDeliveryPicked.publishAfterCommit();
    }

    public static void deliveryRequest(CookFinished cookFinished) {
        //요리가 끝나 음식을 배송대기 상태로 등록
        Delivery delivery = new Delivery();
        delivery.setId(cookFinished.getId());
        delivery.setOrderId(cookFinished.getOrderId());
        delivery.setItemId(cookFinished.getItemId());
        delivery.setStoreId(cookFinished.getStoreId());
        delivery.setCustomerId(cookFinished.getCustomerId());
        delivery.setAddress(cookFinished.getAddress());
        delivery.setStatus("배송대기");
        
        repository().save(delivery);

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
