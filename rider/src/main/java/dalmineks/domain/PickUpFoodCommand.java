package dalmineks.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class PickUpFoodCommand {

    private Long riderId;
    private String riderName;
}
