package dalmineks.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dalmineks.config.kafka.KafkaProcessor;
import dalmineks.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderRejected'"
    )
    public void wheneverOrderRejected_RejectOrder(
        @Payload OrderRejected orderRejected
    ) {
        OrderRejected event = orderRejected;
        System.out.println(
            "\n\n##### listener RejectOrder : " + orderRejected + "\n\n"
        );

        // Comments //
        //다른서비스에서 주문

        // Sample Logic //
        Order.rejectOrder(event);
    }
}
