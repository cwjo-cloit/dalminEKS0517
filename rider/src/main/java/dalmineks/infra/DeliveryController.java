package dalmineks.infra;

import dalmineks.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/deliveries")
@Transactional
public class DeliveryController {

    @Autowired
    DeliveryRepository deliveryRepository;

    @RequestMapping(value = "deliveries/{id}/pickupfood", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public Delivery pickUpFood(@PathVariable(value = "id") Long id, @RequestBody PickUpFoodCommand pickUpFoodCommand, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        System.out.println("##### /delivery/pickUpFood  called #####");
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        
        System.out.println("여기 들어오나?");

        optionalDelivery.orElseThrow(() -> new Exception("No Entity Found"));
        Delivery delivery = optionalDelivery.get();        
        //라이더 정보를 추가해줌
        delivery.setRiderId(pickUpFoodCommand.getRiderId());
        delivery.setRiderName(pickUpFoodCommand.getRiderName());
        delivery.pickUpFood(pickUpFoodCommand);

        deliveryRepository.save(delivery);
        return delivery;
    }
}