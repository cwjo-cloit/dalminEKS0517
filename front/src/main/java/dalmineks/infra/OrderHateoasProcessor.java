package dalmineks.infra;

import dalmineks.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Order>> {

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cnacel")
                .withRel("cnacel")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/deliverycomplete"
                )
                .withRel("deliverycomplete")
        );

        return model;
    }
}
