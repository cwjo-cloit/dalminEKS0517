package dalmineks.domain;

import dalmineks.domain.*;
import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class FoodDeliveryPicked extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long storeId;
    private Long itemId;
    private Long customerId;
    private String address;
    private Long riderId;
    private String riderName;
    private String status;

    public FoodDeliveryPicked(Delivery aggregate) {
        super(aggregate);
    }

    public FoodDeliveryPicked() {
        super();
    }
}
