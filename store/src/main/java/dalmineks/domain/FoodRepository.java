package dalmineks.domain;

import dalmineks.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "foods", path = "foods")
public interface FoodRepository
    extends PagingAndSortingRepository<Food, Long> {}
