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
    FoodRepository foodRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(value = KafkaProcessor.INPUT,condition = "headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_PlaceOrder(@Payload OrderPlaced orderPlaced) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener PlaceOrder : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        Food.placeOrder(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCancelled'"
    )
    public void wheneverOrderCancelled_CancelOrder(
        @Payload OrderCancelled orderCancelled
    ) {
        OrderCancelled event = orderCancelled;
        System.out.println(
            "\n\n##### listener CancelOrder : " + orderCancelled + "\n\n"
        );

        // Sample Logic //
        Food.cancelOrder(event);
    }
}
