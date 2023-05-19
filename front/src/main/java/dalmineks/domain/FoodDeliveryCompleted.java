package dalmineks.domain;

import dalmineks.domain.*;
import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class FoodDeliveryCompleted extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String itemName;
    private Long customerId;
    private Integer price;
    private String status;
    private String address;

    public FoodDeliveryCompleted(Order aggregate) {
        super(aggregate);
    }

    public FoodDeliveryCompleted() {
        super();
    }
}
