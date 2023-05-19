package dalmineks.domain;

import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
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
}
