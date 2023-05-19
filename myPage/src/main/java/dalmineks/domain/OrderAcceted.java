package dalmineks.domain;

import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class OrderAcceted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long ItemId;
    private Long customerId;
    private String address;
    private String status;
    private Long storeId;
    private String storeName;
}
