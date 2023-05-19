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
// @RequestMapping(value="/foods")
@Transactional
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @RequestMapping(
        value = "foods/{id}/acceptorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Food acceptOrder(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /food/acceptOrder  called #####");
        Optional<Food> optionalFood = foodRepository.findById(id);

        optionalFood.orElseThrow(() -> new Exception("No Entity Found"));
        Food food = optionalFood.get();
        food.acceptOrder();

        foodRepository.save(food);
        return food;
    }

    @RequestMapping(
        value = "foods/{id}/rejectorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Food rejectOrder(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /food/rejectOrder  called #####");
        Optional<Food> optionalFood = foodRepository.findById(id);

        optionalFood.orElseThrow(() -> new Exception("No Entity Found"));
        Food food = optionalFood.get();
        food.rejectOrder();

        foodRepository.save(food);
        return food;
    }

    @RequestMapping(
        value = "foods/{id}/startcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Food startCook(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /food/startCook  called #####");
        Optional<Food> optionalFood = foodRepository.findById(id);

        optionalFood.orElseThrow(() -> new Exception("No Entity Found"));
        Food food = optionalFood.get();
        food.startCook();

        foodRepository.save(food);
        return food;
    }

    //요리가 완료되면 이쪽에서 처리    
    @RequestMapping(value = "foods/{id}/finishcook",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Food finishCook(@PathVariable(value = "id") Long id,HttpServletRequest request,HttpServletResponse response) 
    throws Exception {
        System.out.println("##### /food/finishCook  called #####");
        //요청 객체에 대한 유효성 체크
        Optional<Food> optionalFood = foodRepository.findById(id);
        optionalFood.orElseThrow(() -> new Exception("No Entity Found"));

        Food food = optionalFood.get();
        //요리가 완료되었으므로 배송요청 상태로 이벤트 발송
        food.setStatus("배송요청");
        food.finishCook();
        foodRepository.save(food); //변경된 상태 적용

        return food;
    }
}
