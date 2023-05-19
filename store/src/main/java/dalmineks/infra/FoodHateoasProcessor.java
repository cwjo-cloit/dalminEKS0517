package dalmineks.infra;

import dalmineks.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class FoodHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Food>> {

    @Override
    public EntityModel<Food> process(EntityModel<Food> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/acceptorder")
                .withRel("acceptorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/rejectorder")
                .withRel("rejectorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/startcook")
                .withRel("startcook")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/finishcook")
                .withRel("finishcook")
        );

        return model;
    }
}
