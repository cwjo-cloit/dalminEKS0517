package dalmineks.domain;

import dalmineks.domain.*;
import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long itemId;
    private Long customerId;
    private String address;
    private String status;
    private Long storeId;
    private String storeName;

    public CookStarted(Food aggregate) {
        super(aggregate);
    }

    public CookStarted() {
        super();
    }
}
