package dalmineks.domain;

import dalmineks.FrontApplication;
import dalmineks.domain.OrderPaid;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        OrderPaid orderPaid = new OrderPaid(this);
        orderPaid.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }
}
