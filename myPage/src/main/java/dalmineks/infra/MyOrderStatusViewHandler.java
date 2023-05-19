package dalmineks.infra;

import dalmineks.config.kafka.KafkaProcessor;
import dalmineks.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyOrderStatusViewHandler {

    @Autowired
    private MyOrderStatusRepository myOrderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            MyOrderStatus myOrderStatus = new MyOrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
            myOrderStatus.setId(orderPlaced.getId());
            myOrderStatus.setItemName(Long.valueOf(orderPlaced.getItemName()));
            myOrderStatus.setPrice(orderPlaced.getPrice());
            myOrderStatus.setOrderStatus("주문요청");
            // view 레파지 토리에 save
            myOrderStatusRepository.save(myOrderStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAcceted_then_UPDATE_1(
        @Payload OrderAcceted orderAcceted
    ) {
        try {
            if (!orderAcceted.validate()) return;
            // view 객체 조회
            Optional<MyOrderStatus> myOrderStatusOptional = myOrderStatusRepository.findById(
                orderAcceted.getOrderId()
            );

            if (myOrderStatusOptional.isPresent()) {
                MyOrderStatus myOrderStatus = myOrderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myOrderStatus.setOrderStatus("주문승인");
                // view 레파지 토리에 save
                myOrderStatusRepository.save(myOrderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookStarted_then_UPDATE_2(
        @Payload CookStarted cookStarted
    ) {
        try {
            if (!cookStarted.validate()) return;
            // view 객체 조회
            Optional<MyOrderStatus> myOrderStatusOptional = myOrderStatusRepository.findById(
                cookStarted.getOrderId()
            );

            if (myOrderStatusOptional.isPresent()) {
                MyOrderStatus myOrderStatus = myOrderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myOrderStatus.setFoodStatus("조리중");
                // view 레파지 토리에 save
                myOrderStatusRepository.save(myOrderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodDeliveryPicked_then_UPDATE_3(
        @Payload FoodDeliveryPicked foodDeliveryPicked
    ) {
        try {
            if (!foodDeliveryPicked.validate()) return;
            // view 객체 조회
            Optional<MyOrderStatus> myOrderStatusOptional = myOrderStatusRepository.findById(
                foodDeliveryPicked.getOrderId()
            );

            if (myOrderStatusOptional.isPresent()) {
                MyOrderStatus myOrderStatus = myOrderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myOrderStatus.setDeliveryStatus("배송 중");
                // view 레파지 토리에 save
                myOrderStatusRepository.save(myOrderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenFoodDeliveryCompleted_then_UPDATE_4(
        @Payload FoodDeliveryCompleted foodDeliveryCompleted
    ) {
        try {
            if (!foodDeliveryCompleted.validate()) return;
            // view 객체 조회
            Optional<MyOrderStatus> myOrderStatusOptional = myOrderStatusRepository.findById(
                foodDeliveryCompleted.getId()
            );

            if (myOrderStatusOptional.isPresent()) {
                MyOrderStatus myOrderStatus = myOrderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myOrderStatus.setOrderStatus("거래완료");
                // view 레파지 토리에 save
                myOrderStatusRepository.save(myOrderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
