package application.repositories;
import application.models.Review;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "review", path = "review")
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    Review findById(long id);
    List<Review> findAll();
}