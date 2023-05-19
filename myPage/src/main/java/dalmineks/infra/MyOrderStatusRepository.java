package dalmineks.infra;

import dalmineks.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "myOrderStatuses",
    path = "myOrderStatuses"
)
public interface MyOrderStatusRepository
    extends PagingAndSortingRepository<MyOrderStatus, Long> {}
