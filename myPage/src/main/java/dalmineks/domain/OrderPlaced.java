package dalmineks.domain;

import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String itemName;
    private Long customerId;
    private Integer price;
    private String status;
    private String address;
}
