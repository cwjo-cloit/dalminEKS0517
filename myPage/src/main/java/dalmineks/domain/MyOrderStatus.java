package dalmineks.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MyOrderStatus_table")
@Data
public class MyOrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long itemName;
    private Integer price;
    private String orderStatus;
    private String foodStatus;
    private String deliveryStatus;
}
