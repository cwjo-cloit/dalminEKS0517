package dalmineks.domain;

import dalmineks.domain.*;
import dalmineks.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPaid extends AbstractEvent {

    private Long id;

    public OrderPaid(Payment aggregate) {
        super(aggregate);
    }

    public OrderPaid() {
        super();
    }
}
