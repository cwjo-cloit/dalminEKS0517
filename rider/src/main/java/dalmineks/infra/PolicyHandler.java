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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookFinished'"
    )
    public void wheneverCookFinished_DeliveryRequest(
        @Payload CookFinished cookFinished
    ) {
        CookFinished event = cookFinished;
        System.out.println(
            "\n\n##### listener DeliveryRequest : " + cookFinished + "\n\n"
        );

        // Sample Logic //
        Delivery.deliveryRequest(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FoodDeliveryCompleted'"
    )
    public void wheneverFoodDeliveryCompleted_DeliveryFinish(
        @Payload FoodDeliveryCompleted foodDeliveryCompleted
    ) {
        FoodDeliveryCompleted event = foodDeliveryCompleted;
        System.out.println(
            "\n\n##### listener DeliveryFinish : " +
            foodDeliveryCompleted +
            "\n\n"
        );

        // Sample Logic //
        Delivery.deliveryFinish(event);
    }
}
