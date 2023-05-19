package dalmineks.domain;

import dalmineks.domain.*;
import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderRejected extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long itemId;
    private Long customerId;
    private String address;
    private String status;
    private Long storeId;
    private String storeName;

    public OrderRejected(Food aggregate) {
        super(aggregate);
    }

    public OrderRejected() {
        super();
    }
}
